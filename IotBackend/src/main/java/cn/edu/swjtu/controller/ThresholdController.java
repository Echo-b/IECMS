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
    public ResponseData setDeviceTempThreshold(@RequestBody Threshold th){
        return thresholdService.changeTempThreshold(th);
    }
    @PostMapping("/set/humidity")
    public ResponseData setDeviceHumiThreshold(@RequestBody Threshold th){
        return thresholdService.changeHumiThreshold(th);
    }
    @PostMapping("/set/light")
    public ResponseData setDeviceLightThreshold(@RequestBody Threshold th){
        return thresholdService.changeLightThreshold(th);
    }
}
