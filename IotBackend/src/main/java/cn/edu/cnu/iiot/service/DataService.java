package cn.edu.cnu.iiot.service;

import cn.edu.cnu.iiot.result.ResponseData;
import org.apache.ibatis.annotations.Select;

public interface DataService {
    /**
     * @author echobai
     * @param pageNum
     * @return
     */
    ResponseData getNormalData(int pageNum);

    /**
     * @author echobai
     * @param did
     * @return
     */
    ResponseData getNormalDataById(int did);

    /**
     * @author echobai
     * @param dno
     * @return
     */
    ResponseData deleteNormalData(int dno);

    ResponseData getNewestNormalData(int did);

    /**
     * @author echobai
     * @param did
     * @return
     */
    ResponseData getAlertData(int did);

    /**
     * @author echobai
     * @param pageNum
     * @return
     */
    ResponseData getAlertDataById(int pageNum);

    /**
     * @author echobai
     * @param dno
     * @return
     */
    ResponseData deleteAlertData(int dno);

    ResponseData getNewestAlertData(int did);

    ResponseData getAverageTemperature(int did);

}
