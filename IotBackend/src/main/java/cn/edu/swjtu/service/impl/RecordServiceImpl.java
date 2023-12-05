package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.RecordMapper;
import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.MQTTService;
import cn.edu.swjtu.service.RecordService;
import cn.edu.swjtu.task.DelayQueueManager;
import cn.edu.swjtu.task.DelayTask;
import cn.edu.swjtu.task.TaskBase;
import cn.edu.swjtu.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper mapper;

    private MQTTService mqttService;
    private DelayQueueManager delayQueueManager;


    @Override
    public int InsertCommandRecord(CommandInfo c){
        int res = 0;
        try {
            res = mapper.InsertCommandRecord(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  res;
    }

    @Override
    public ResponseData getAllCommandRecord(String operator) {
        ArrayList<CommandInfo> commandInfos = mapper.getAllCommandRecord(operator);
        return ResponseData.success("success").data("records",commandInfos);
    }

    public ResponseData InsertCommandByScheudle(CommandInfo c) throws InterruptedException {
        try {
            c.setDate(DateUtil.getDate());
            mapper.InsertCommandRecord(c);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("add failure");
        }
        if(c.getParam() != ""){
            delayQueueManager.put(new DelayTask(new TaskBase(c),10 * 1000 * Integer.parseInt(c.getParam()) ));
        }else{
            mqttService.pubMqttMsg(c);
        }
        return ResponseData.success("success");
    }
}
