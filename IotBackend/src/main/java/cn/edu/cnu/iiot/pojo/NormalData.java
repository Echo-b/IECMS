package cn.edu.cnu.iiot.pojo;

import lombok.Data;

@Data
public class NormalData {
    private int did;
    private int dno;
    private String date;
    private float temperature;


    public NormalData(int dno, int did, float temperature,String date) {
        this.did = did;
        this.dno = dno;
        this.date = date;
        this.temperature = temperature;
    }

    public NormalData() {}
}
