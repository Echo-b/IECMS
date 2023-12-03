package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.Init;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InitMapper {
    @Select("select * from init")
    Init getInitParam();
}
