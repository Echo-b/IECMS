package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.result.ResponseData;

import java.text.ParseException;

public interface UserService {
    ResponseData UserLogin(User u);
    ResponseData GetUserInfo(String token);
    ResponseData UserLogout(String token);
    ResponseData UpdateUserInfo(User u);
    ResponseData getAllGroupUser(int group_id);
    ResponseData addGroupUser(User u);
}
