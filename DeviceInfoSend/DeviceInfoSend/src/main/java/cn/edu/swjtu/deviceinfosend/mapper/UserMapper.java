package cn.edu.swjtu.deviceinfosend.mapper;
import cn.edu.swjtu.deviceinfosend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("select password from user where username = #{username};")
    String getSingleUserInfo(String username);

    @Select("select * from user;")
    ArrayList<User> getAllUser();

    @Update("update user set password = #{newPassword} where username = #{username};")
    int UpdatePassword(String newPassword, String username);

}
