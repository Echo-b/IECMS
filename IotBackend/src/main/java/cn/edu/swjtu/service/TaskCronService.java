package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.TaskCron;

import java.util.List;

public interface TaskCronService {
    public List<TaskCron> selectTaskCron();

    public void updateTaskCron(int id);
}
