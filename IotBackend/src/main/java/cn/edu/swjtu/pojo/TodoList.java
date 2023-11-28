package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class TodoList {
    private Integer tid;
    private String date;
    private String apply;
    private String deviceName;
    private int did;
    private int status;

    public TodoList(String apply,String deviceName,int did,int status){
        this.apply = apply;
        this.deviceName = deviceName;
        this.did = did;
        this.status = status;
    }
    public TodoList(){}
}
