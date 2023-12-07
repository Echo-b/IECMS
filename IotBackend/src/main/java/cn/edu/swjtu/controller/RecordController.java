package cn.edu.swjtu.controller;

import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.MQTTService;
import cn.edu.swjtu.service.RecordService;
import cn.edu.swjtu.task.DelayQueueManager;
import cn.edu.swjtu.task.DelayTask;
import cn.edu.swjtu.task.TaskBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.parseLong;

@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService service;

    @Autowired
    private MQTTService mqttService;

    @Autowired
    private DelayQueueManager delayQueueManager;

    @PostMapping("/insert")
    public ResponseData InsertCommandRecord(@RequestBody CommandInfo c){
        System.out.println("c = " + c);
        // if command has params then we should set delay task
        if(!c.getParam().equals("")){
            delayQueueManager.put(new DelayTask(new TaskBase(c),parseLong(c.getParam()) * 1000));
        }
        return mqttService.pubMqttMsg(c);
    }

    @GetMapping("/all")
    public ResponseData getAllCommandRecord(String operator){
        return service.getAllCommandRecord(operator);
    }

    @GetMapping("/activity")
    public ResponseData getGroupActivity(int group_id) {
        return service.getGroupActivity(group_id);
    }

}
