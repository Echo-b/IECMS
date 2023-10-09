package cn.edu.cnu.iiot.mapper;

import cn.edu.cnu.iiot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String getSingleUserInfo(String username);

    @Select("select * from user;")
    ArrayList<User> getAllUser();

    @Update("update user set password = #{newPassword} where username = #{username};")
    int UpdatePassword(String newPassword, String username);

}
