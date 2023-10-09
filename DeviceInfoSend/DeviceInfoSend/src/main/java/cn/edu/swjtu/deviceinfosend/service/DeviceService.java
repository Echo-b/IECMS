package cn.edu.swjtu.deviceinfosend.service;

import cn.edu.swjtu.deviceinfosend.pojo.ControllInfo;
import cn.edu.swjtu.deviceinfosend.pojo.Device;
import cn.edu.swjtu.deviceinfosend.pojo.DeviceAction;
import cn.edu.swjtu.deviceinfosend.result.ResponseData;

public interface DeviceService {
    ResponseData getDevices(int pageNum);

    ResponseData getDeviceById(int did);

    ResponseData deleteDeviceById(int did);

    ResponseData addDevice(Device device);

    ResponseData editDevice(Device device);

    int controllDevice(ControllInfo c);

}
