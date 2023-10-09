package cn.edu.cnu.iiot.service.impl;

import cn.edu.cnu.iiot.dto.GetDevicesResp;
import cn.edu.cnu.iiot.mapper.DeviceActionMapper;
import cn.edu.cnu.iiot.mapper.DeviceMapper;
import cn.edu.cnu.iiot.pojo.Device;
import cn.edu.cnu.iiot.pojo.DeviceAction;
import cn.edu.cnu.iiot.result.ResponseData;
import cn.edu.cnu.iiot.service.DeviceService;
import cn.edu.cnu.iiot.service.MqttActions;
import cn.edu.cnu.iiot.task.DelayQueueManager;
import cn.edu.cnu.iiot.task.DelayTask;
import cn.edu.cnu.iiot.task.TaskBase;
import cn.edu.cnu.iiot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper mapper;

    @Autowired
    private MqttActions mqttActions;

    @Autowired
    private DeviceActionMapper deviceActionMapper;

    @Autowired
    private DelayQueueManager delayQueueManager;

    @Autowired
    void init(){
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
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("resp.devices = " + resp.devices.size());
        return ResponseData.success("success").data("items",resp).data("size",resp.devices.size()).data("total",total);
    }

    @Override
    public ResponseData getDeviceById(int did) {
        Device device = new Device();
        try {
            device = mapper.getDeviceById(did);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.success("success").data("item",device);
    }

    @Override
    public ResponseData deleteDeviceById(int did) {
        if(mapper.deleteById(did) == 1) {
            return ResponseData.success("delete success");
        } else{
            return  ResponseData.error("delete failure");
        }
    }

    @Override
    public ResponseData addDevice(Device device) {
        int res = 0;
        try{
            res = mapper.addDevice(device);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(res == 1){
            return ResponseData.success("add success");
        }else {
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
        if(res == 1){
            return ResponseData.success("success");
        }else {
            return ResponseData.error("edit failure");
        }
    }

    @Override
    public void mqttMsgProcess(String msg) {
        System.out.println("msg = " + msg);
    }

    @Override
    public ResponseData DeviceAction(DeviceAction deviceAction) throws InterruptedException {
        try {
            deviceAction.setDate(DateUtil.getDate());
            deviceActionMapper.insertDeviceAction(deviceAction);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("add failure");
        }
        if(deviceAction.getParam() != ""){
            delayQueueManager.put(new DelayTask(new TaskBase(deviceAction),10 * 1000));
        }else{
            mqttActions.pubMqttMsg(deviceAction.getDeviceName(),deviceAction.getAction());
        }
        return ResponseData.success("success");
    }

}
