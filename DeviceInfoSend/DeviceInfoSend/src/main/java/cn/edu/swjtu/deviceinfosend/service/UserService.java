package cn.edu.swjtu.deviceinfosend.service;

import cn.edu.swjtu.deviceinfosend.pojo.User;
import cn.edu.swjtu.deviceinfosend.pojo.updatePassword;
import cn.edu.swjtu.deviceinfosend.result.ResponseData;

public interface UserService {
    ResponseData UserLogin(User u);
    ResponseData GetUserInfo(String token);
    ResponseData UserLogout();
    ResponseData UpdatePassword(updatePassword up);
}
