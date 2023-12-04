package cn.edu.swjtu.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlertInfo  implements Serializable {
    private int dno;
    private int did;
    private float temperature;
    private float humidity;
    private float light;
    private String sender;
    private String date;

    public AlertInfo(){}

    public AlertInfo(int dno,int did, float temperature, String sender, String date) {
        this.dno = dno;
        this.did = did;
        this.temperature = temperature;
        this.sender = sender;
        this.date = date;
    }



}
