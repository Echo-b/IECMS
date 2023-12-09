package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.DataMapper;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.mapper.UserMapper;
import cn.edu.swjtu.pojo.*;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.ChartDataService;
import cn.edu.swjtu.utils.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xfvape.uid.UidGenerator;
import jakarta.annotation.Resource;
import org.apache.tomcat.Jar;
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

    @Autowired
    private DataMapper dataMapper;

    private String[] weeks = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
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

    @Override
    public ResponseData getLightData() throws ParseException {
        try {
            JSONObject cnt = new JSONObject();
            JSONObject data = new JSONObject();
            ArrayList<AverageData> allAverageData = dataMapper.getAllAverageData();
            for(String key : zhweeks) {
                cnt.put(key, 0);
                data.put(key,0);
            }
            for(AverageData ele : allAverageData) {
                String key = DateToDay(ele.getDate());
                cnt.put(key,cnt.getIntValue(key) +1);
                data.put(key,data.getDoubleValue(key) + ele.getLight());
            }
            Vector<Double> lightData = new Vector<>();
            for(int i = 0; i < weeks.length; ++i){
                lightData.add((Double) data.getDoubleValue(zhweeks[i]) / cnt.getIntValue(zhweeks[i]));
            }
            return ResponseData.success("获取温一周光照数据成功").data("lightData",lightData);
        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseData.error("获取一周光照数据失败");
    }

    @Override
    public ResponseData getRadderData() {
        try {
            AverageData data = dataMapper.getNewestAverageData();
            int[] value;
            JSONArray datas = new JSONArray();
            // todo: add complex way to compute the weight, the baseline is ten
            //  { name: 'dressing' },
            //  { name: 'sun protection' },
            //  { name: 'travel' },
            //  { name: 'sports' },
            //  { name: 'fishing' },
            //  { name: 'Car Wash' }
            // handle light
            if(data.getLight() >= 300 ){
                JSONObject item = new JSONObject();
                value = new int[]{5, 8, 5, 5, 8, 5};
                item.put("name","light");
                item.put("value",value);
                datas.add(item);
            } else if (data.getLight() <= 100 ){
                JSONObject item = new JSONObject();
                value = new int[]{2, 2, 8, 8, 8, 2};
                item.put("name","light");
                item.put("value",value);
                datas.add(item);
            } else {
                JSONObject item = new JSONObject();
                value = new int[]{5, 5, 7, 7, 8, 2};
                item.put("name","Light");
                item.put("value",value);
                datas.add(item);
            }

            // handle temperature
            if(data.getTemperature() >= 35 ){
                JSONObject item = new JSONObject();
                value = new int[]{3, 6, 6, 6, 8, 9};
                item.put("name","temperature");
                item.put("value",value);
                datas.add(item);
            } else if (data.getTemperature() <= 15 ){
                JSONObject item = new JSONObject();
                value = new int[]{8, 2, 6, 7, 8, 1};
                item.put("name","temperature");
                item.put("value",value);
                datas.add(item);
            } else {
                JSONObject item = new JSONObject();
                value = new int[]{5, 5, 7, 7, 8, 2};
                item.put("name","temperature");
                item.put("value",value);
                datas.add(item);
            }

            // handle humidity
            if(data.getHumidity() >= 65 ){
                JSONObject item = new JSONObject();
                value = new int[]{2, 1, 4, 3, 8, 1};
                item.put("name","humidity");
                item.put("value",value);
                datas.add(item);
            } else if (data.getHumidity() <= 20 ){
                JSONObject item = new JSONObject();
                value = new int[]{3, 2, 9, 9, 8, 6};
                item.put("name","humidity");
                item.put("value",value);
                datas.add(item);
            } else {
                JSONObject item = new JSONObject();
                value = new int[]{5, 5, 7, 7, 8, 2};
                item.put("name","humidity");
                item.put("value",value);
                datas.add(item);
            }
            return ResponseData.success("获取最新温湿度数据成功").data("radarData",datas);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("获取最新温湿度数据失败");
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

        int[] expectedData = new int[]{7, 12, 14, 10, 8, 15, 17};

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

    @Override
    public ResponseData getPieData() throws ParseException {
        ArrayList<AlertInfo> alertInfos = (ArrayList<AlertInfo>) redisTemplate.opsForValue().get("alertInfos");
        JSONObject data = new JSONObject();
        for(String key : zhweeks) {
            data.put(key, 0);
        }
        for(AlertInfo ele : alertInfos) {
            String key = DateToDay(ele.getDate());
            data.put(key,data.getIntValue(key) +1);
        }
        JSONObject legend = new JSONObject();
        legend.put("data",weeks);
        JSONObject series = new JSONObject();
        series.put("data",remap(data));

        JSONObject chart = new JSONObject();
        chart.put("legend",legend);
        chart.put("series",series);
        return ResponseData.success("获取数据成功").data("chart",chart);
    }
}
