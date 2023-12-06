package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.Threshold;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ThresholdMapper {
    @Select("select * from threshold where did = #{did};")
    Threshold getDeviceThreshold(int did);

    @Insert("insert into threshold did,temp_max,humi_max,light_max) " +
            "values (#{did},#{temp_max},#{humi_max},#{light_max});")
    int InsertDeviceThreshold(Threshold th);

    @Update("update threshold set temp_max = #{temp_max} where did = #{did};")
    int changeTempThreshold(int did);
    @Update("update threshold set humi_max = #{humi_max} where did = #{did};")
    int changeHumiThreshold(int did);
    @Update("update threshold set light_max = #{light_max} where did = #{did};")
    int changeLightThreshold(int did);
}