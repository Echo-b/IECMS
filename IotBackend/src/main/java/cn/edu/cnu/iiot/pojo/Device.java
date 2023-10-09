package cn.edu.cnu.iiot.pojo;

import lombok.Data;

@Data
public class Device {
    private Integer did;
    private String deviceName;
    private String status;
    private float longitude;
    private float latitude;
}
