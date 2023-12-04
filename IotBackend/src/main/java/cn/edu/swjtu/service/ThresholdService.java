package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.Threshold;
import cn.edu.swjtu.result.ResponseData;

public interface ThresholdService {
    ResponseData getDeviceThreshold(int did);
    ResponseData InsertDeviceThreshold(Threshold th);
    ResponseData changeLightThreshold(int did);
    ResponseData changeHumiThreshold(int did);
    ResponseData changeTempThreshold(int did);
}
