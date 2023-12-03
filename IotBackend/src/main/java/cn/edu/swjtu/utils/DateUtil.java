package cn.edu.swjtu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        return date;
    }

    public static String getWeek(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(str);
        sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }
}