package cn.edu.cnu.iiot.pojo;

import lombok.Data;

@Data
public class AlertInfo {
    private int dno;
    private int did;
    private float temperature;
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
