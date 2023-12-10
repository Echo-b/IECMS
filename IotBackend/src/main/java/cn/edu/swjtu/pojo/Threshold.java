package cn.edu.swjtu.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Threshold implements Serializable {
    private int did;
    private float temp_max;
    private float humi_max;
    private float light_max;
}
