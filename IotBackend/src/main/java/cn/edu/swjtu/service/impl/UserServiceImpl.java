package cn.edu.swjtu.service.impl;
import cn.edu.swjtu.mapper.UserMapper;
import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.UserService;
import cn.edu.swjtu.utils.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import com.xfvape.uid.UidGenerator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.lang.Long.parseLong;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Resource
    private UidGenerator uidGenerator;

    @Override
    public ResponseData UserLogin(User u) {
//        ResponseData r = new ResponseData()
        User user = new User();
        try {
            user = mapper.getSingleUserInfo(u.getUsername());
            System.out.println(user.getPassword_hash());
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(user.getPassword_hash().equals(u.getPassword_hash())){
            System.out.println(u.getUsername());
            String token  = JwtUtils.generateToken(u.getUsername());
            return ResponseData.success("登录成功").data("token",token);
        } else {
            return ResponseData.error("用户名或密码错误");
        }
    }

    @Override
    public ResponseData GetUserInfo(String token) {
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        System.out.println("username = " + username);
        User user = mapper.getSingleUserInfo(username);
        return ResponseData.success("success").data("uid",user.getUid()).data("name",username).data("avatar",user.getAvatar()).data("roles",user.getRoles()).data("email",user.getEmail()).data("groupid",user.getGroup_id());
    }

    @Override
    public ResponseData UserLogout() {
        return ResponseData.success("success");
    }

    @Override
    public ResponseData UpdateUserInfo(User u) {
       if(mapper.UpdateUserInfo(u) > 0 ){
           return ResponseData.success("更新信息成功");
        }
        return ResponseData.error("更新信息失败");
    }

    @Override
    public ResponseData getAllGroupUser(int group_id) {
        try{
            ArrayList<User> groups = mapper.getAllGroupUser(group_id);
            if(groups.size() > 0)
                return ResponseData.success("获取小组用户数据成功").data("groups",groups).data("groups_nums",groups.size());
        } catch (Exception e){
            e.printStackTrace();
        }
       return ResponseData.error("获取小组用户数据失败");
    }

    @Override
    public ResponseData addGroupUser(User u) {
        try {
            if(mapper.addGroupUser(u) > 0){
                return ResponseData.success("成功添加小组成员");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("添加小组成员失败");
    }

    @Override
    public String UidToDateString(String username) {
        User u = mapper.getSingleUserInfo(username);
        JSONObject createinfo= JSONObject.parseObject(uidGenerator.parseUID(parseLong(u.getUid())));
        return createinfo.get("timestamp").toString();
    }
}
