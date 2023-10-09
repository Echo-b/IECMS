package cn.edu.cnu.iiot.controller;

import cn.edu.cnu.iiot.result.ResponseData;
import cn.edu.cnu.iiot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/normal/all")
    public ResponseData getNormalData(@RequestParam(defaultValue = "1") int pageNum) {
        return dataService.getNormalData(pageNum);
    }

    @DeleteMapping("/normal/delete")
    public ResponseData deleteNormalData(int dno){
        return dataService.deleteNormalData(dno);
    }

    @GetMapping("/normal/get")
    public ResponseData getNormalDataById(int did){
        return dataService.getNormalDataById(did);
    }

    @GetMapping("/normal/getnewest")
    public ResponseData getNewestNormalData(int did) {
        return dataService.getNewestNormalData(did);
    }

    @GetMapping("/alert/all")
    public ResponseData getAlertData(@RequestParam(defaultValue = "1") int pageNum){
        return dataService.getAlertData(pageNum);
    }

    @DeleteMapping("/alert/delete")
    public ResponseData deleteAlertData(int dno){
        return dataService.deleteAlertData(dno);
    }

    @GetMapping("/alert/get")
    public ResponseData getAlertDataById(int did){
        return dataService.getAlertDataById(did);
    }

    @GetMapping("/alert/getnewest")
    public ResponseData getNewestAlertData(int did) {
        return dataService.getNewestAlertData(did);
    }

    @GetMapping("/average/get")
    public ResponseData getAverageTemperature(int did){
        return dataService.getAverageTemperature(did);
    }
}
