package cn.edu.swjtu.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NormalData  implements Serializable {
    private int did;
    private int dno;
    private String date;
    private float temperature;
    private float humidity;
    private float light;


    public NormalData(int dno, int did, float temperature,String date) {
        this.did = did;
        this.dno = dno;
        this.date = date;
        this.temperature = temperature;
    }

    public NormalData() {}
}
