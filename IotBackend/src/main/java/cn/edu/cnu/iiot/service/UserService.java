package cn.edu.cnu.iiot.service;

import cn.edu.cnu.iiot.pojo.User;
import cn.edu.cnu.iiot.pojo.updatePassword;
import cn.edu.cnu.iiot.result.ResponseData;

public interface UserService {
    ResponseData UserLogin(User u);
    ResponseData GetUserInfo(String token);
    ResponseData UserLogout();
    ResponseData UpdatePassword(updatePassword up);
}
