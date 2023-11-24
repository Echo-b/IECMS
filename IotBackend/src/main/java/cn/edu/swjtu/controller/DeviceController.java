package cn.edu.swjtu.controller;

import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.pojo.Device;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.DeviceService;
import cn.edu.swjtu.service.MQTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/appointpage")
    public ResponseData getAppointPageDevices(@RequestParam(defaultValue = "1") int pageNum) {
        System.out.println("pageNum = " + pageNum);
        return deviceService.getAppointPageDevices(pageNum);
    }
    @GetMapping("/all")
    public ResponseData getAppointPageDevices() {
        return deviceService.getAllDevices();
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

}
