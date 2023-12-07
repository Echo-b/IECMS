package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.RecordMapper;
import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.RecordService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int InsertCommandRecord(CommandInfo c){
        int res = 0;
        try {
            res = mapper.InsertCommandRecord(c);
            //todo : check command include params? if it has params then we should set task
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  res;
    }

    @Override
    public ResponseData getAllCommandRecord(String operator) {
        ArrayList<CommandInfo> commandInfos = mapper.getAllCommandRecord(operator);
        return ResponseData.success("success").data("records",commandInfos);
    }

    @Override
    public ResponseData getGroupActivity(int group_id) {
        try {
            ArrayList<User> users = (ArrayList<User>) redisTemplate.opsForValue().get("usergroup" + group_id);
            JSONArray activity = new JSONArray();
            for (User u: users) {
                JSONObject item = new JSONObject();
                item.put("avatar",u.getAvatar());
                item.put("username",u.getUsername());
                try {
                    CommandInfo c = mapper.getUserNewestActivity(u.getUsername());
                    item.put("command",c.getCommand());
                    item.put("timestamp", c.getDate());
                    item.put("summary",c.getOperator()+ " " + c.getCommand() + " " + c.getDeviceName());
                }catch (Exception e){
                    e.printStackTrace();
                }
                activity.add(item);
            }
            return ResponseData.success("获取小组成员活动信息成功").data("activity",activity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("获取小组成员活动信息失败");
    }
}
