package cn.edu.swjtu.mapper;
import cn.edu.swjtu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username};")
    User getSingleUserInfo(String username);

    @Select("select * from user;")
    ArrayList<User> getAllUser();

    @Update("update user set avatar = #{avatar}, email = #{email} where username = #{username};")
    int UpdateUserInfo(User u);

}
