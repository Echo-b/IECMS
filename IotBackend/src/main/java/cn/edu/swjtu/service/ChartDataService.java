package cn.edu.swjtu.service;


import cn.edu.swjtu.result.ResponseData;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Vector;

public interface ChartDataService {
    ResponseData getChartData(String username) throws ParseException;

    ResponseData getTotalNums(String username) throws  ParseException;

    ResponseData getPieData() throws ParseException;
    String UidToDateString(String username) throws ParseException;
    String DateToDay(String date) throws ParseException;
    Vector<Integer> remap(JSONObject data);

}
