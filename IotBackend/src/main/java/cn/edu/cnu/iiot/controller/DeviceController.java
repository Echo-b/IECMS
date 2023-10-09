package cn.edu.cnu.iiot.controller;

import cn.edu.cnu.iiot.pojo.Device;
import cn.edu.cnu.iiot.pojo.DeviceAction;
import cn.edu.cnu.iiot.result.ResponseData;
import cn.edu.cnu.iiot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
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

    @PostMapping("/action")
    public ResponseData DeviceAction(DeviceAction deviceAction) throws InterruptedException {
        return deviceService.DeviceAction(deviceAction);
    }

}
