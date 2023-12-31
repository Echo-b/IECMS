package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.Threshold;
import cn.edu.swjtu.result.ResponseData;

public interface ThresholdService {
    ResponseData getDeviceThreshold(int did);
    ResponseData InsertDeviceThreshold(Threshold th);
    ResponseData changeLightThreshold(Threshold th);
    ResponseData changeHumiThreshold(Threshold th);
    ResponseData changeTempThreshold(Threshold th);
}
