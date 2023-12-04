package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.Threshold;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ThresholdMapper {
    @Select("select * from threshold where did = #{did};")
    Threshold getDeviceThreshold(int did);

    @Insert("insert into threshold (tid,did,temp_max,humi_max,light_max) " +
            "values (#{tid},#{did},#{temp_max},#{humi_max},#{light_max});")
    int InsertDeviceThreshold(Threshold th);
}
