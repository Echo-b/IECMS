package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class Device {
    private Integer did;
    private String deviceName;
    private String status;
    private float longitude;
    private float latitude;
    private String creator;
    private Integer group_id;
    private String type;
    private int insert_flag;
}
