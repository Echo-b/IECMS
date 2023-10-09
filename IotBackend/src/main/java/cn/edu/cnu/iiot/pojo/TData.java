package cn.edu.cnu.iiot.pojo;

import lombok.Data;

@Data
public class TData {
    private int did;
    private float temperature;
    private String date;

    public TData(int did, float temperature, String date) {
        this.did = did;
        this.temperature = temperature;
        this.date = date;
    }

    public TData(){}
}
