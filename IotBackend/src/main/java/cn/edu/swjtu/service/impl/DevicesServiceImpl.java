package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.dto.GetDevicesResp;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.pojo.ControllInfo;
import cn.edu.swjtu.pojo.Device;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.DeviceService;
import cn.edu.swjtu.service.MQTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper mapper;

    @Autowired
    private MQTTService mqttActions;

    @Autowired
    void init() {
        mqttActions.subMqttMsg();
    }

    @Override
    public ResponseData getDevices(int pageNum) {
        int total = 0;
        GetDevicesResp resp = new GetDevicesResp();
        try {
            int num = (pageNum - 1) * 10;
            resp.devices = mapper.getDevices(num);
            total = mapper.countDeviceNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("resp.devices = " + resp.devices.size());
        return ResponseData.success("success").data("items", resp).data("size", resp.devices.size()).data("total", total);
    }

    @Override
    public ResponseData getDeviceById(int did) {
        Device device = new Device();
        try {
            device = mapper.getDeviceById(did);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseData.success("success").data("item", device);
    }

    @Override
    public ResponseData deleteDeviceById(int did) {
        if (mapper.deleteById(did) == 1) {
            return ResponseData.success("delete success");
        } else {
            return ResponseData.error("delete failure");
        }
    }

    @Override
    public ResponseData addDevice(Device device) {
        int res = 0;
        try {
            res = mapper.addDevice(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res == 1) {
            return ResponseData.success("add success");
        } else {
            return ResponseData.error("add failure");
        }
    }

    @Override
    public ResponseData editDevice(Device device) {
        int res = 0;
        try {
            res = mapper.editDevice(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res == 1) {
            return ResponseData.success("success");
        } else {
            return ResponseData.error("edit failure");
        }
    }

    @Override
    public int controllDevice(ControllInfo c){
        int res = 0;
        try {
            res = mapper.DeviceControll(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  res;
    }

}


