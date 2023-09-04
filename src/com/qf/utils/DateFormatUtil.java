package com.qf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    //时间->字符串
    public static  String format(Date date,String pattern){
//          格式化时间的类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }
    //字符串->时间
    public static Date pares(String date,String pattern){
//          格式化时间的类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date pares=null;
        try {
            pares = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }return pares;
    }

}
