package cn.edu.cnu.iiot.task;

import cn.edu.cnu.iiot.pojo.DeviceAction;
import lombok.Data;

@Data
public class TaskBase {
    //任务参数，根据业务需求多少都行
    private DeviceAction deviceAction;

    public TaskBase(DeviceAction deviceAction) {
        this.deviceAction = deviceAction;
    }
}
