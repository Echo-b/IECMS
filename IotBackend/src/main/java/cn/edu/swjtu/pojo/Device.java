package cn.edu.swjtu.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Device implements Serializable {
    private Integer did;
    private String deviceName;
    private String status;
    private float longitude;
    private float latitude;
    private String creator;
    private Integer group_id;
    private String type;
    private int insert_flag;
    private String date;
}
