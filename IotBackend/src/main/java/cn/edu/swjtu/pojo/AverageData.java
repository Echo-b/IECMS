package cn.edu.swjtu.pojo;

import lombok.Data;

@Data
public class AverageTemperature {
    private int dno;
    private int did;
    private float temperature;
    private float humidity;
    private float light;
    private String date;
}
