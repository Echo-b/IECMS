package cn.edu.swjtu.controller;

import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.UserService;
import com.xfvape.uid.UidGenerator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    private UidGenerator uidGenerator;

    @PostMapping("/login")
    public ResponseData UserLogin(@RequestBody User user){
        System.out.println("uid = " + uidGenerator.getUID());
        return userService.UserLogin(user);
    }

    @GetMapping("/info")
    public ResponseData GetUserInfo(String token){
        return userService.GetUserInfo(token);
    }

    @PostMapping("/logout")
    public ResponseData UserLogout(){
        return userService.UserLogout();
    }

    @PostMapping("/update")
    public  ResponseData updateUserInfos(@RequestBody User u){
        System.out.println("u = " + u);
        return userService.UpdateUserInfo(u);
    }
}
