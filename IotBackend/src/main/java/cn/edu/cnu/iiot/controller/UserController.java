package cn.edu.cnu.iiot.controller;

import cn.edu.cnu.iiot.pojo.User;
import cn.edu.cnu.iiot.pojo.updatePassword;
import cn.edu.cnu.iiot.result.ResponseData;
import cn.edu.cnu.iiot.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public ResponseData UserLogin(@RequestBody User user){
        System.out.println("user = " + user);
        return userService.UserLogin(user);
    }

    @GetMapping("/user/info")
    public ResponseData GetUserInfo(String token){
        return userService.GetUserInfo(token);
    }

    @PostMapping("/user/logout")
    public ResponseData UserLogout(){
        return userService.UserLogout();
    }

    @PostMapping("/user/update")
    public  ResponseData updatePassword(@RequestBody updatePassword up){
        return userService.UpdatePassword(up);
    }
}
