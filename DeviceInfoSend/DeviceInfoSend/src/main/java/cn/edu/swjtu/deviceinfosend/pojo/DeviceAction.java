package cn.edu.swjtu.deviceinfosend.pojo;

import lombok.Data;

@Data
public class DeviceAction {
    private String deviceName;
    private String action;
    private String param;
    private String date;

    public DeviceAction(String deviceName, String action, String param, String date) {
        this.deviceName = deviceName;
        this.action = action;
        this.param = param;
        this.date = date;
    }
    public DeviceAction(){}
}
