package cn.edu.swjtu.controller;

import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.MQTTService;
import cn.edu.swjtu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService service;

    @Autowired
    private MQTTService mqttService;

    @PostMapping("/insert")
    public ResponseData InsertCommandRecord(@RequestBody CommandInfo c){
        System.out.println("c = " + c);
        return mqttService.pubMqttMsg(c);
    }

    @GetMapping("/all")
    public ResponseData getAllCommandRecord(String operator){
        return service.getAllCommandRecord(operator);
    }

    @PostMapping("/scheudle")
    public ResponseData InsertCommandByScheudle(CommandInfo c) throws InterruptedException {
            return service.InsertCommandByScheudle(c);
    }
}
