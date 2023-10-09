package cn.edu.swjtu.deviceinfosend.pojo;

import lombok.Data;

@Data
public class ControllInfo {
    private int did;
    private String command;
    private String deviceName;
    private String topic;
}
