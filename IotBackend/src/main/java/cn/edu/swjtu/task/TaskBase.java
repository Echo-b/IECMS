package cn.edu.swjtu.task;

import cn.edu.swjtu.pojo.CommandInfo;
import lombok.Data;

import javax.swing.*;

@Data
public class TaskBase {
    //任务参数，根据业务需求多少都行
    private CommandInfo commandInfo;

    public TaskBase(CommandInfo deviceAction) {
        this.commandInfo = deviceAction;
    }


}
