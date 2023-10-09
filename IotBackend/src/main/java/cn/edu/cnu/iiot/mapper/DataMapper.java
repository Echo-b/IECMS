package cn.edu.cnu.iiot.mapper;

import cn.edu.cnu.iiot.pojo.AlertInfo;
import cn.edu.cnu.iiot.pojo.AverageTemperature;
import cn.edu.cnu.iiot.pojo.NormalData;
import cn.edu.cnu.iiot.pojo.TData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface DataMapper {
    /**
     * @author echobai
     * @param pageNum
     * @return
     */
    @Select("select * from normal_data where dno > #{num} limit 10")
    ArrayList<NormalData> getNormalData(int pageNum);


    /**
     * @author echobai
     * @param did
     * @return
     */
    @Select("select * from normal_data where did = #{did}")
    ArrayList<NormalData> getNormalDataById(int did);

    /**
     * @author echobai
     * @param dno
     * @return
     */
    @Delete("delete from normal_data where dno = #{dno};")
    int deleteNormalData(int dno);

    /**
     *
     * @param normalData
     * @return
     */
    @Insert("insert into normal_data (did, temperature, date) values (#{did},#{temperature},#{date});")
    int insertNoramlData(NormalData normalData);

    @Select("select * from normal_data where did = #{did} order by date DESC limit 1")
    NormalData getNewestNormalData(int did);

    /**
     *
     * @return
     */
    @Select("select count(*) from normal_data;")
    int countNoramlDataTotalNum();

    /**
     * @author echobai
     * @param pageNum
     * @return
     */
    @Select("select * from alert_info where dno > #{num} limit 10")
    ArrayList<AlertInfo> getAlertData(int pageNum);

    /**
     * @author echobai
     * @param did
     * @return
     */
    @Select("select * from alert_info where did = #{did}")
    ArrayList<AlertInfo> getAlertDataById(int did);

    /**
     * @author echobai
     * @param dno
     * @return
     */
    @Delete("delete from alert_info where dno = #{dno};")
    int deleteAlertData(int dno);

    /**
     *
     * @param alertInfo
     * @return
     */
    @Insert("insert into alert_info (temperature, sender, date, did) values (#{temperature},#{sender},#{date},#{did});")
    int insertAlertInfo(AlertInfo alertInfo);

    /**
     *
     * @return
     */
    @Select("select count(*) from alert_info;")
    int countAlertDataTotalNum();

    @Select("select * from alert_info where did = #{did} order by date DESC limit 1")
    AlertInfo getNewestAlertData(int did);

    @Select("select did , avg(temperature) as temperature\n" +
            "from normal_data\n" +
            "where did = #{did}\n" +
            "group by did limit 10;")
    ArrayList<TData> getData(int did);

    @Insert("insert into average_temperature (did, temperature, date) values (#{did},#{temperature},#{date});")
    void insertAverageTemperature(TData a);

    @Select("select did,temperature,date from average_temperature where did = #{did} ;")
    ArrayList<TData> getAverageTemperature(int did);
}
