package cn.edu.cnu.iiot.service.impl;

import cn.edu.cnu.iiot.mapper.UserMapper;
import cn.edu.cnu.iiot.pojo.User;
import cn.edu.cnu.iiot.pojo.updatePassword;
import cn.edu.cnu.iiot.result.ResponseData;
import cn.edu.cnu.iiot.service.UserService;
import cn.edu.cnu.iiot.utils.GenerateAvatar;
import cn.edu.cnu.iiot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public ResponseData UserLogin(User u) {
//        ResponseData r = new ResponseData();
        String password = new String();
        try {
            password = mapper.getSingleUserInfo(u.getUsername());
            System.out.println(password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(password.equals(u.getPassword())){
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
//        String url = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        String avatar = GenerateAvatar.generate(username);
        return ResponseData.success("success").data("name",username).data("avatar",avatar);
    }

    @Override
    public ResponseData UserLogout() {
        return ResponseData.success("success");
    }

    @Override
    public ResponseData UpdatePassword(updatePassword up) {
        String password = new String();
        try {
            password = mapper.getSingleUserInfo(up.getUsername());
            System.out.println(password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(password.equals(up.getOldPassword())){
            int flag = 0;
            try {
                flag = mapper.UpdatePassword(up.getNewPassword(),up.getUsername());
            }catch (Exception e){
                e.printStackTrace();
            }
            if(flag == 1)
                return ResponseData.success("修改成功");
            else
                return ResponseData.error("修改失败");
        } else {
            return ResponseData.error("密码错误");
        }
    }
}
