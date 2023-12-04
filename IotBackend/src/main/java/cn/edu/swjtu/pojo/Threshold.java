package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class Threshold {
    private int did;
    private float temp_max;
    private float humi_max;
    private float light_max;
}
