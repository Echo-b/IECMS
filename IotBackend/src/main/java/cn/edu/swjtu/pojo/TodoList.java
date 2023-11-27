package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class TodoList {
    private int tid;
    private String date;
    private String apply;
    private String deviceName;
    private int status;
}
