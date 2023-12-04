package cn.edu.swjtu.controller;

import cn.edu.swjtu.pojo.Threshold;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.ThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/threshold")
public class ThresholdController {
    @Autowired
    private ThresholdService thresholdService;

    @GetMapping("/get")
    public ResponseData getDeviceThreshold(int did){
        return thresholdService.getDeviceThreshold(did);
    }
    @PostMapping("/setting")
    public ResponseData setDeviceThreshold(@RequestBody Threshold th){
        return thresholdService.InsertDeviceThreshold(th);
    }

    @PostMapping("/set/temperature")
    public ResponseData setDeviceTempThreshold(int did){
        return thresholdService.changeTempThreshold(did);
    }
    @PostMapping("/set/humidity")
    public ResponseData setDeviceHumiThreshold(int did){
        return thresholdService.changeHumiThreshold(did);
    }
    @PostMapping("/set/light")
    public ResponseData setDeviceLightThreshold(int did){
        return thresholdService.changeLightThreshold(did);
    }
}
