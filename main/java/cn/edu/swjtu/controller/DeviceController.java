package cn.edu.swjtu.controller;

import cn.edu.swjtu.mapper.DeviceActionMapper;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.pojo.ControllInfo;
import cn.edu.swjtu.pojo.Device;
import cn.edu.swjtu.pojo.DeviceAction;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.DeviceService;
import cn.edu.swjtu.service.MQTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MQTTService mqttService;

    @Autowired
    DeviceMapper deviceMapper;
    @GetMapping("/all")
    public ResponseData getDevices(@RequestParam(defaultValue = "1") int pageNum) {
        System.out.println("pageNum = " + pageNum);
        return deviceService.getDevices(pageNum);
    }

    @PostMapping("/add")
    public ResponseData addDevice(Device device){
        return deviceService.addDevice(device);
    }

    @GetMapping("/get")
    public ResponseData getDeviceById(int did){
        return deviceService.getDeviceById(did);
    }

    @DeleteMapping("/delete")
    public ResponseData deleteDeviceById(int did){
        return deviceService.deleteDeviceById(did);
    }

    @PostMapping("/edit")
    public ResponseData editDevice(Device device){
        return deviceService.editDevice(device);
    }

    @PostMapping("/control")
    public ResponseData controlDevice(ControllInfo c) {
        System.out.println("c = " + c);
        return mqttService.pubMqttMsg(c);
    }

     /*
    @author yyx
    查找设备信息
     */

    @GetMapping("/find")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String deviceName,
                                        @RequestParam String action) {

        pageNum = (pageNum - 1) * pageSize;
        deviceName = "%" + deviceName + "%"; //前端数据交互拼写
        action = "%" + action + "%";//前端数据交互拼写
        ArrayList<DeviceAction> data = deviceMapper.selectPage(pageNum, pageSize, deviceName);
        Integer total = deviceMapper.selectTotal(deviceName);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

}
