package cn.edu.swjtu.service.impl;
import cn.edu.swjtu.mapper.UserMapper;
import cn.edu.swjtu.pojo.User;
import cn.edu.swjtu.pojo.updatePassword;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.UserService;
import cn.edu.swjtu.utils.GenerateAvatar;
import cn.edu.swjtu.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

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
//        String url = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        //String avatar = GenerateAvatar.generate(username);
        String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        User user = mapper.getSingleUserInfo(username);
        return ResponseData.success("success").data("name",username).data("avatar",user.getAvatar()).data("roles",user.getRoles());
    }

    @Override
    public ResponseData UserLogout() {
        return ResponseData.success("success");
    }

    @Override
    public ResponseData UpdatePassword(updatePassword up) {
        User user = new User();
        try {
            user = mapper.getSingleUserInfo(up.getUsername());
            System.out.println(user.getPassword_hash());
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(user.getPassword_hash().equals(up.getOldPassword())){
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
