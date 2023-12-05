package cn.edu.swjtu.task;


import cn.edu.swjtu.pojo.CommandInfo;
import lombok.Data;

@Data
public class TaskBase {
    //任务参数，根据业务需求多少都行
    private CommandInfo commandInfo;

    public TaskBase(CommandInfo c) {
        this.commandInfo = c;
    }
}
