package cn.edu.cnu.iiot.service.impl;

import cn.edu.cnu.iiot.mapper.DataMapper;
import cn.edu.cnu.iiot.pojo.AlertInfo;
import cn.edu.cnu.iiot.pojo.NormalData;
import cn.edu.cnu.iiot.pojo.TData;
import cn.edu.cnu.iiot.result.ResponseData;
import cn.edu.cnu.iiot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private DataMapper mapper;

    @Override
    public ResponseData getNormalData(int pageNum) {
        ArrayList<NormalData> normalData = new ArrayList<>();
        int total = 0;
        try {
            int num = (pageNum - 1) * 10;
            normalData = mapper.getNormalData(num);
            total = mapper.countNoramlDataTotalNum();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseData.error("failure");
        }
        return ResponseData.success("success").data("items",normalData).data("size",normalData.size()).data("total",total);
    }

    @Override
    public ResponseData getNormalDataById(int did) {
        ArrayList<NormalData> normalData;
        try {
            normalData = mapper.getNormalDataById(did);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseData.error("failure");
        }
        return ResponseData.success("success").data("items",normalData);
    }

    @Override
    public ResponseData deleteNormalData(int dno) {
        int flag = 0;
        try {
            flag = mapper.deleteNormalData(dno);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(flag == 1){
            return ResponseData.success("success");
        } else {
            return ResponseData.error("failure");
        }
    }

    @Override
    public ResponseData getNewestNormalData(int did) {
        NormalData n = new NormalData();
        try {
            n = mapper.getNewestNormalData(did);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("failure");
        }
        return ResponseData.success("获取最新数据成功").data("item",n);
    }

    @Override
    public ResponseData getAlertData(int pageNum) {
        ArrayList<AlertInfo> alertInfos = new ArrayList<>();
        int total = 0;
        try {
            int num = (pageNum - 1) * 10;
            alertInfos = mapper.getAlertData(num);
            total = mapper.countAlertDataTotalNum();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseData.error("failure");
        }
        return ResponseData.success("success").data("items",alertInfos).data("size",alertInfos.size()).data("total",total);
    }

    @Override
    public ResponseData getAlertDataById(int did) {
        ArrayList<AlertInfo> alertInfos = new ArrayList<>();
        try {
            alertInfos = mapper.getAlertDataById(did);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseData.error("failure");
        }
        return ResponseData.success("success").data("items",alertInfos);
    }

    @Override
    public ResponseData deleteAlertData(int dno) {
        int flag = 0;
        try {
            flag = mapper.deleteAlertData(dno);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(flag == 1){
            return ResponseData.success("success");
        } else {
            return ResponseData.error("failure");
        }
    }

    @Override
    public ResponseData getNewestAlertData(int did) {
        AlertInfo alertInfo = new AlertInfo();
        try {
            alertInfo = mapper.getNewestAlertData(did);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("failure");
        }
        return ResponseData.success("获取最新数据成功").data("item",alertInfo);
    }

    @Override
    public ResponseData getAverageTemperature(int did) {
        ArrayList<TData> average = new ArrayList<>();
        try {
            average = mapper.getAverageTemperature(did);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseData.error("failure");
        }
        return ResponseData.success("success").data("items",average);
    }
}
