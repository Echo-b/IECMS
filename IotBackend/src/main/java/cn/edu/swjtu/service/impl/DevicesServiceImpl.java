package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.dto.GetDevicesResp;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.pojo.Device;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.DeviceService;
import cn.edu.swjtu.service.MQTTService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public ResponseData getAppointPageDevices(int pageNum) {
        int total = 0;
        GetDevicesResp resp = new GetDevicesResp();
        try {
            int num = (pageNum - 1) * 10;
            resp.devices = mapper.getAppointPageDevices(num);
            total = mapper.countDeviceNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("resp.devices = " + resp.devices.size());
        return ResponseData.success("success").data("items", resp).data("size", resp.devices.size()).data("total", total);
    }

    @Override
    public ResponseData getAllGroupDevices(int group_id) {
        int total = 0;
        GetDevicesResp resp = new GetDevicesResp();
        try {
            resp.devices = mapper.getAllGroupDevices(group_id);
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
        try {
            if(mapper.addDevice(device) > 0) {
                return ResponseData.success("add success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseData.error("add failure");
    }

    @Override
    public ResponseData editDevice(Device device) {
        try {
            if(mapper.editDevice(device) > 0) {
                return ResponseData.success("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseData.error("edit failure");
    }

    @Override
    public ResponseData changeDeviceFlag(int did) {
        try {
            if(mapper.changeDeviceFlag(did) > 0){
                return ResponseData.success("设备标志已更新");
            }
        }catch(Exception e){
         e.printStackTrace();
        }
        return ResponseData.error("设备标志更新失败");
    }

    @Override
    public ResponseData getThresholdDevices(String username) {
        try {
            ArrayList<Device> thresholdDevice = mapper.getThresholdDevices(username);
            JSONArray thresDevice = new JSONArray();
            for(Device device : thresholdDevice){
                JSONObject item = new JSONObject();
                item.put("did",device.getDid());
                item.put("deviceName",device.getDeviceName());
                item.put("type",device.getType());
                thresDevice.add(item);
            }
            return ResponseData.success("获取阈值设备信息成功").data("thresDevice",thresDevice);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("获取阈值设备信息失败");
    }

}


