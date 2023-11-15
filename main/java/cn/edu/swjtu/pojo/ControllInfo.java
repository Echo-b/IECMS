package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class ControllInfo {
    private int did;
    private String command;
    private String deviceName;
    private String topic;
    private String date;
}
