package cn.edu.swjtu.utils;

import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataHandle {
    @Autowired
    private UserService userService;

    private String[] weeks = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
//    public <T> Map<String,Object> mapDataIntoWeeks(ArrayList<T> data){
//        Map<String,Object> mapdata = null;
//        for(T ele : data) {
//
//        }
//        return mapdata;
//    }

    public HashMap<String,Integer> mapUsersIntoWeeks(ArrayList<User> users){
        HashMap<String,Integer> mapdata = new HashMap<String,Integer>();
        for(String key : weeks) {
            mapdata.put(key, 0);
        }
        for(User ele : users) {
            String key = String.valueOf(mapdata.get(userService.UidToDateString(ele.getUsername())));
            int cnt = mapdata.get(key);
            ++cnt;
            mapdata.put(key,cnt);
        }
        return mapdata;
    }
}
