package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.TaskCron;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface TaskCronMapper {

    @Select("select * from taskCron order by id;")
    List<TaskCron> selectTaskCron();

    @Update("set finish = 1 where id = #{id};")
    Void updateTaskCron(int id);
}
