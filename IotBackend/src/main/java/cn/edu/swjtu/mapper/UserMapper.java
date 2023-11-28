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

    @Select("select * from user where group_id = #{group_id};")
    ArrayList<User> getAllGroupUser(int group_id);

    @Update("update user set username = #{username}, avatar = #{avatar}, email = #{email} where uid = #{uid};")
    int UpdateUserInfo(User u);

}
