package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.Device;
import cn.edu.swjtu.result.ResponseData;

public interface DeviceService {
    ResponseData getAppointPageDevices(int pageNum);

    ResponseData getAllDevices();

    ResponseData getDeviceById(int did);

    ResponseData deleteDeviceById(int did);

    ResponseData addDevice(Device device);

    ResponseData editDevice(Device device);

}
