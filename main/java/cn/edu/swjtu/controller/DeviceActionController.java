package cn.edu.swjtu.controller;

import cn.edu.swjtu.mapper.DeviceActionMapper;
import cn.edu.swjtu.pojo.DeviceAction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeviceActionController {

    DeviceActionMapper deviceActionMapper;

    /*
    @author yyx
    查找设备活动-DeviceAction信息
     */
    @GetMapping("/find")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String deviceName,
                                        @RequestParam String action) {

        pageNum = (pageNum - 1) * pageSize;
        deviceName = "%" + deviceName + "%"; //前端数据交互拼写
        action = "%" + action + "%";//前端数据交互拼写
        ArrayList<DeviceAction> data = deviceActionMapper.selectPage(pageNum, pageSize, deviceName, action);
        Integer total = deviceActionMapper.selectTotal(deviceName, action);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

}
