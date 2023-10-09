package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.pojo.updatePassword;
import cn.edu.swjtu.result.ResponseData;

public interface UserService {
    ResponseData UserLogin(User u);
    ResponseData GetUserInfo(String token);
    ResponseData UserLogout();
    ResponseData UpdatePassword(updatePassword up);
}
