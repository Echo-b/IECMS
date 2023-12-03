package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.DataMapper;
import cn.edu.swjtu.mapper.DeviceMapper;
import cn.edu.swjtu.mapper.UserMapper;
import cn.edu.swjtu.pojo.AlertInfo;
import cn.edu.swjtu.pojo.Device;
import cn.edu.swjtu.pojo.NormalData;
import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.ChartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Service
public class ChartDataServiceImpl implements ChartDataService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ResponseData getChartData(String username) {
        User u = userMapper.getSingleUserInfo(username);
        int group_id = u.getGroup_id();

        // get all data include users, devices etc.
        ArrayList<User> groups = userMapper.getAllGroupUser(group_id);
        ArrayList<Device> devices = deviceMapper.getAllGroupDevices(group_id);
        ArrayList<NormalData> normalDatas = dataMapper.getAllNormalData();
        ArrayList<AlertInfo> alertInfos = dataMapper.getAllAlertData();

        // handle data and map them into different weekdays.
        Map<String,Object> chartdatas = null;
        chartdatas.put("groupnums",groups.size());
        chartdatas.put("devicenums",devices.size());
//        chartdatas.put("expectDataForUser",);
//        chartdatas.put("actualDataForUser",);
//        chartdatas.put("expectDataForDevice",);
//        chartdatas.put("actualDataForDevice",);
//        chartdatas.put("expectDataForNormal",);
//        chartdatas.put("actualDataForNormal",);
//        chartdatas.put("expectDataForAlert",);
//        chartdatas.put("actualDataForAlert",);
        return ResponseData.success("获取数据成功").data("chartdatas",chartdatas);
    }
}
