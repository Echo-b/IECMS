package cn.edu.swjtu.mapper;


import cn.edu.swjtu.pojo.ControllInfo;
import cn.edu.swjtu.pojo.Device;
import org.apache.ibatis.annotations.*;


import java.util.ArrayList;

@Mapper
public interface DeviceMapper {

    @Select("select * from device where did > #{num} limit 10")
    ArrayList<Device> getDevices(int num);

    @Insert("replace into device(did, deviceName, status, longitude, latitude) values (#{did}," +
            "#{deviceName},#{status},#{longitude},#{latitude});")
    int editDevice(Device device);

    @Select("select * from device where did = #{did};")
    Device getDeviceById(int id);

    @Delete("delete from device where did = #{did};")
    int deleteById(int did);

    @Insert("insert into device (did,deviceName,status,longitude,latitude)" +
            "values (#{did},#{deviceName},#{status},#{longitude},#{latitude});")
    int addDevice(Device device);

    @Select("select count(*) from device;")
    int countDeviceNum();

    @Insert("insert into command_record (did,command,deviceName,topic) values" +
            "(#{did},#{command},#{deviceName},#{topic})")
    int DeviceControll(ControllInfo c);


}
