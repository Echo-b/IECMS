package cn.edu.cnu.iiot.test;

import cn.edu.cnu.iiot.pojo.User;
import cn.edu.cnu.iiot.utils.DateUtil;
import cn.edu.cnu.iiot.utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JsonTest {
    @Test
    public void test(){
//        User user = new User("echo","12345");
//        String token  = JwtUtils.generateToken(user.getUsername());
//        System.out.println("token = " + token);
        String date1 = DateUtil.getDate();
        String date2 = DateUtil.getDate();
        System.out.println(date2.compareTo(date1));
    }

}
