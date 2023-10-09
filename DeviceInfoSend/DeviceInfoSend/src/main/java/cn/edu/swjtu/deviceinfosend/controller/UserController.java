package cn.edu.swjtu.deviceinfosend.controller;

import cn.edu.swjtu.deviceinfosend.pojo.User;
import cn.edu.swjtu.deviceinfosend.pojo.updatePassword;
import cn.edu.swjtu.deviceinfosend.result.ResponseData;
import cn.edu.swjtu.deviceinfosend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;


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
