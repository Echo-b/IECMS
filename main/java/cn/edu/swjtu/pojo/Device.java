package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class Device {
    private Integer did;
    private String deviceName;
    private String status;
    private float longitude;
    private float latitude;
}
