package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.mapper.UserMapper;
import cn.edu.swjtu.pojo.AlertInfo;
import cn.edu.swjtu.pojo.Device;
import cn.edu.swjtu.pojo.NormalData;
import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.ChartDataService;
import cn.edu.swjtu.utils.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.xfvape.uid.UidGenerator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

import static java.lang.Long.parseLong;

@Service
public class ChartDataServiceImpl implements ChartDataService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Autowired
    private RedisTemplate redisTemplate;

    private String[] zhweeks = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};


    @Override
    public String UidToDateString(String username) throws ParseException {
        User u = userMapper.getSingleUserInfo(username);
//        User u = (User) redisTemplate.opsForValue().get(username);
        JSONObject createinfo= JSONObject.parseObject(uidGenerator.parseUID(parseLong(u.getUid())));
        String day = createinfo.get("timestamp").toString();
        return DateUtil.getWeek(day);
    }

    @Override
    public String DateToDay(String date) throws ParseException {
        return DateUtil.getWeek(date);
    }

    @Override
    public Vector<Integer> remap(JSONObject data) {
        Vector<Integer> remaps = new Vector<>();
        for(int i = 0; i < data.size(); ++i){
            remaps.add(data.getIntValue(zhweeks[i]));
        }
        return remaps;
    }

    public Vector<Integer> mapUsersIntoWeeks(ArrayList<User> users) throws ParseException {
        JSONObject mapdata = new JSONObject();
        for(String key : zhweeks) {
            mapdata.put(key, 0);
        }
        for(User ele : users) {
            String key = UidToDateString(ele.getUsername());
            mapdata.put(key,mapdata.getIntValue(key) +1);
        }
        return remap(mapdata);
    }
    public Vector<Integer> mapDeviceIntoWeeks(ArrayList<Device> devices) throws ParseException {
        JSONObject mapdata = new JSONObject();
        for(String key : zhweeks) {
            mapdata.put(key, 0);
        }
        for(Device ele : devices) {
            String key = DateToDay(ele.getDate());
            mapdata.put(key,mapdata.getIntValue(key) +1);
        }
        return remap(mapdata);
    }

    public Vector<Integer> mapNormalIntoWeeks(ArrayList<NormalData> normalData) throws ParseException {
        JSONObject mapdata = new JSONObject();
        for(String key : zhweeks) {
            mapdata.put(key, 0);
        }
        for(NormalData ele : normalData) {
            String key = DateToDay(ele.getDate());
            mapdata.put(key,mapdata.getIntValue(key) +1);
        }
        return remap(mapdata);
    }

    public Vector<Integer> mapAlertIntoWeeks(ArrayList<AlertInfo> alertInfos) throws ParseException {
        JSONObject mapdata = new JSONObject();
        for(String key : zhweeks) {
            mapdata.put(key, 0);
        }
        for(AlertInfo ele : alertInfos) {
            String key = DateToDay(ele.getDate());
            mapdata.put(key,mapdata.getIntValue(key) +1);
        }
        return remap(mapdata);
    }

    @Override
    public ResponseData getChartData(String username) throws ParseException {
//        User u = userMapper.getSingleUserInfo(username);
        User u = (User)redisTemplate.opsForValue().get(username);
        int group_id = u.getGroup_id();

        // get all data include users, devices etc.
        ArrayList<User> groups = (ArrayList<User>) redisTemplate.opsForValue().get("usergroup" + group_id);
        ArrayList<Device> devices = (ArrayList<Device>) redisTemplate.opsForValue().get("devicegroup" + group_id);
        ArrayList<NormalData> normalDatas = (ArrayList<NormalData>)redisTemplate.opsForValue().get("normalDatas");
        ArrayList<AlertInfo> alertInfos = (ArrayList<AlertInfo>) redisTemplate.opsForValue().get("alertInfos");

        // handle data and map them into different weekdays.
        JSONObject datas = new JSONObject();

        Vector<Integer> expectedData = new Vector<>(7,10);

        JSONObject groupdatas = new JSONObject();
        groupdatas.put("expectedData",expectedData);
        groupdatas.put("actualData",mapUsersIntoWeeks(groups));

        JSONObject devicedatas = new JSONObject();
        devicedatas.put("expectedData",expectedData);
        devicedatas.put("actualData",mapDeviceIntoWeeks(devices));

        JSONObject normaldatas = new JSONObject();
        normaldatas.put("expectedData",expectedData);
        normaldatas.put("actualData",mapNormalIntoWeeks(normalDatas));

        JSONObject warningdatas = new JSONObject();
        warningdatas.put("expectedData",expectedData);
        warningdatas.put("actualData",mapAlertIntoWeeks(alertInfos));

        datas.put("groupdatas",groupdatas);
        datas.put("devicedatas",devicedatas);
        datas.put("normaldatas",normaldatas);
        datas.put("warningdatas",warningdatas);

        return ResponseData.success("获取数据成功").data("datas",datas);
    }

    @Override
    public ResponseData getTotalNums(String username) throws ParseException {
        User u = (User)redisTemplate.opsForValue().get(username);
        int group_id = u.getGroup_id();
        // get all data include users, devices etc.
        ArrayList<User> groups = (ArrayList<User>) redisTemplate.opsForValue().get("usergroup" + group_id);
        ArrayList<Device> devices = (ArrayList<Device>) redisTemplate.opsForValue().get("devicegroup" + group_id);
        ArrayList<NormalData> normalDatas = (ArrayList<NormalData>)redisTemplate.opsForValue().get("normalDatas");
        ArrayList<AlertInfo> alertInfos = (ArrayList<AlertInfo>) redisTemplate.opsForValue().get("alertInfos");

        // handle data and map them into different weekdays.
        JSONObject datas = new JSONObject();
        datas.put("groupnums",groups.size());
        datas.put("devicenums",devices.size());
        datas.put("normaldatanums",normalDatas.size());
        datas.put("alterinfonums",alertInfos.size());

        return ResponseData.success("获取数据成功").data("totalnums",datas);
    }
}
