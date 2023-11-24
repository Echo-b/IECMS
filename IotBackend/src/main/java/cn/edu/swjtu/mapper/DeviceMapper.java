package cn.edu.swjtu.mapper;


import cn.edu.swjtu.pojo.Device;
import org.apache.ibatis.annotations.*;


import java.util.ArrayList;

@Mapper
public interface DeviceMapper {

    @Select("select * from device limit #{num},10")
    ArrayList<Device> getAppointPageDevices(int num);

    @Select("select * from device")
    ArrayList<Device> getAllDevices();

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

}