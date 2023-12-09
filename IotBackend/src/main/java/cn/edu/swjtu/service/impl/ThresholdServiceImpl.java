package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.ThresholdMapper;
import cn.edu.swjtu.pojo.Threshold;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.ThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ThresholdServiceImpl implements ThresholdService {
    @Autowired
    private ThresholdMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public ResponseData getDeviceThreshold(int did) {
        try {
            Threshold th = mapper.getDeviceThreshold(did);
            return ResponseData.success("获取设备阈值参数成功").data("threshold",th);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("获取设备阈值参数失败");
    }

    @Override
    public ResponseData InsertDeviceThreshold(Threshold th) {
        try {
            if(mapper.InsertDeviceThreshold(th) > 0)
                return ResponseData.success("设备阈值参数设置成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("设备阈值参数设置失败");
    }

    @Override
    public ResponseData changeLightThreshold(Threshold th) {
        try {
            if(mapper.changeLightThreshold(th) > 0){
                redisTemplate.opsForValue().set(th.getDid(),th);
                return ResponseData.success("修改关照强度阈值成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("修改光照强度阈值失败");
    }

    @Override
    public ResponseData changeHumiThreshold(Threshold th) {
        try {
            if(mapper.changeHumiThreshold(th) > 0){
                redisTemplate.opsForValue().set(th.getDid(),th);
                return ResponseData.success("修改湿度阈值成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("修改湿度阈值失败");
    }

    @Override
    public ResponseData changeTempThreshold(Threshold th) {
        try {
            if(mapper.changeTempThreshold(th) > 0){
                redisTemplate.opsForValue().set(th.getDid(),th);
                return ResponseData.success("修改温度阈值成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("修改温度阈值失败");
    }
}
