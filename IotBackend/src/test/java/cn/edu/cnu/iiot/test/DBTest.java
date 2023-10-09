package cn.edu.cnu.iiot.test;

import cn.edu.cnu.iiot.mapper.DataMapper;
import cn.edu.cnu.iiot.mapper.UserMapper;
import cn.edu.cnu.iiot.pojo.NormalData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testAdd(){

    }
}
