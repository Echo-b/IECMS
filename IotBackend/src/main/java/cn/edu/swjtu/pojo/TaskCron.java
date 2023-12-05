package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class TaskCron {
    /**
     * 所选作业类型:
     * 0  -> 每分钟
     * 1  -> 每小时
     * 2  -> 每天
     * 3  -> 每周
     * 4  -> 每月
     * 5  -> 每年
     */
    private Integer type;
    private Integer id;
    //启动时间
    private String date;
    private String creat_time;
    private boolean finish;


    public Integer getJobType() {
        return type;
    }

    public void setJobType(Integer jobType) {
        this.type = jobType;
    }

    public String getStartDate() {
        return date;
    }

    public void setStartDate(String startDate) {
        this.date = startDate;
    }

    public boolean getFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
    public TaskCron(Integer id, Integer jobType, String startDate, String creat_time, boolean finish) {
        this.id = id;
        this.creat_time = creat_time;
        this.type = jobType;
        this.date = startDate;
        this.finish = finish;
    }

}
