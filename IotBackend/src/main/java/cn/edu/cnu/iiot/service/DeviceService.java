package cn.edu.cnu.iiot.service;

import cn.edu.cnu.iiot.pojo.Device;
import cn.edu.cnu.iiot.pojo.DeviceAction;
import cn.edu.cnu.iiot.result.ResponseData;

public interface DeviceService {
    ResponseData getDevices(int pageNum);

    ResponseData getDeviceById(int did);

    ResponseData deleteDeviceById(int did);

    ResponseData addDevice(Device device);

    ResponseData editDevice(Device device);

    void mqttMsgProcess(String msg);

    ResponseData DeviceAction(DeviceAction deviceAction) throws InterruptedException;

}
