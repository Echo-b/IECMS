package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.CommandInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface RecordMapper {
    @Insert("insert into command_record (did,command,deviceName,topic,date,operator,param) values" +
            "(#{did},#{command},#{deviceName},#{topic},#{date},#{operator},#{param})")
    int InsertCommandRecord(CommandInfo c);

    @Select("select * from command_record where operator = #{operator}")
    ArrayList<CommandInfo> getAllCommandRecord(String operator);

    @Select("select * from command_record where operator = #{operator} order by date DESC limit 1")
    CommandInfo getUserNewestActivity(String operator);
}
