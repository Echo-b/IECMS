package cn.edu.swjtu.init;

import cn.edu.swjtu.mapper.DataMapper;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.mapper.InitMapper;
import cn.edu.swjtu.mapper.UserMapper;
import cn.edu.swjtu.pojo.AlertInfo;
import cn.edu.swjtu.pojo.Init;
import cn.edu.swjtu.pojo.NormalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Order(1) // 初始化加载优先级，数字越小优先级越高
public class InitData implements ApplicationRunner {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private InitMapper initMapper;
    @Override
    public void run(ApplicationArguments args) {
        System.out.println(".....Init data into redis......");
        // search database init params
        Init init = initMapper.getInitParam();

        // store them by group id
        for(int id = 0; id < init.getGroups(); ++id){
            redisTemplate.opsForValue().set("usergroup" + id,userMapper.getAllGroupUser(id));
            redisTemplate.opsForValue().set("devicegroup" + id,deviceMapper.getAllGroupDevices(id));
        }

        ArrayList<NormalData> normalDatas = dataMapper.getAllNormalData();
        ArrayList<AlertInfo> alertInfos = dataMapper.getAllAlertData();
        redisTemplate.opsForValue().set("normalDatas",normalDatas);
        redisTemplate.opsForValue().set("alertInfos",alertInfos);
        System.out.println(".....Init data successful......");
    }
}