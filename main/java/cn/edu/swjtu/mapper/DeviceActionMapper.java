package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.DeviceAction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface DeviceActionMapper {
    @Insert("insert into device_action(deviceName, action, param,date) values (#{deviceName}, #{action},#{param},#{date});")
    int insertDeviceAction(DeviceAction deviceAction);

    /**
     *@author yyx
     * 在device_action中查找
     */
    @Select("select * from  device_action where deviceName like #{deviceName} and action like #{action} limit #{pageNum} , #{pageSize}")
    ArrayList<DeviceAction> selectPage(Integer pageNum, Integer pageSize, String deviceName, String action);
    @Select("select count(*) from  device_action where deviceName like #{deviceName} and action like #{action}")
    Integer  selectTotal(String deviceName, String action);
}
