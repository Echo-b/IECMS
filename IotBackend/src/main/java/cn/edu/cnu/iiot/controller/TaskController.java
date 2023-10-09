package cn.edu.cnu.iiot.controller;

import cn.edu.cnu.iiot.pojo.TaskParams;
import cn.edu.cnu.iiot.result.ResponseData;
import cn.edu.cnu.iiot.task.ScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final ScheduleTask scheduleTask;

    @Autowired
    public TaskController(ScheduleTask scheduleTask) {
        this.scheduleTask = scheduleTask;
    }

    @PostMapping("/update")
    public ResponseData update(@RequestBody TaskParams taskParams) {
        System.out.println("taskParams = " + taskParams);
        if(taskParams.getCorn() != "") {
            scheduleTask.setCron(taskParams.getCorn());
        }else{
            scheduleTask.setTimer((long) taskParams.getTimer() * 1000);
        }
        return ResponseData.success("success");
    }

    @GetMapping("/get")
    public ResponseData getCorn(){
        String corn  = scheduleTask.getCron();
        Long timer = scheduleTask.getTimer();
        return ResponseData.success("success").data("corn",corn).data("timer",timer);
    }

}