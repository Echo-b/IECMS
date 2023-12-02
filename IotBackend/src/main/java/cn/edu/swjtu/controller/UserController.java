package cn.edu.swjtu.controller;

import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseData UserLogin(@RequestBody User user){
        return userService.UserLogin(user);
    }

    @GetMapping("/info")
    public ResponseData GetUserInfo(String token){
        return userService.GetUserInfo(token);
    }

    @PostMapping("/logout")
    public ResponseData UserLogout(String token){
        return userService.UserLogout(token);
    }

    @PostMapping("/update")
    public  ResponseData updateUserInfos(@RequestBody User u){
        System.out.println("u = " + u);
        return userService.UpdateUserInfo(u);
    }
    @GetMapping("/groups")
    public ResponseData getAllGroupUser(int group_id){
        return userService.getAllGroupUser(group_id);
    }
}
