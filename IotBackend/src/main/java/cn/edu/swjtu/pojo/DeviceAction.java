package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class DeviceAction {
    private int aid;
    private String deviceName;
    private int did;
    private String action;
    private String param;
    private String date;

    public DeviceAction(String deviceName,int did,  String action, String param, String date) {
        this.deviceName = deviceName;
        this.did = did;
        this.action = action;
        this.param = param;
        this.date = date;
    }
    public DeviceAction(){}
}
