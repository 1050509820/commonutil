package com.basic.IoTCardPlatform.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author: Henry1901
 * @Date: 2020/3/2 10:50
 * @Version 1.0
 */
public class DateUtil {

    /**
     * 时间转double
     * @param date
     * @return 返回值类似：43322.3770190278
     */
    public static double Date2Double(Date date){
        @SuppressWarnings("deprecation")
        long localOffset  = date.getTimezoneOffset()*60000;
        double dd = (double)(date.getTime()-localOffset)/ 24 / 3600 / 1000 + 25569.0000000;
        DecimalFormat df = new DecimalFormat("#.0000000000");//先默认保留10位小数

        return Double.valueOf(df.format(dd));
    }

    /**
     * double 转  Date 时间
     * @param dVal
     */
    public static Date DoubleToDate(Double dVal){
        Date oDate = new Date();
        @SuppressWarnings("deprecation")
        long localOffset = oDate.getTimezoneOffset() * 60000; //系统时区偏移 1900/1/1 到 1970/1/1 的 25569 天
        oDate.setTime((long) ((dVal - 25569) * 24 * 3600 * 1000 + localOffset));

        return oDate;
    }

    // long转换为Date类型
    public static Date longToDate(long currentTime) {
        Date date = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        return date;
    }


    // date转换为long类型
    public static long date2Long(Date date) {
        long time = date.getTime();
        return time;
    }

    /**
     * Date转String(格式自定义)
     *
     * @param pattern yyyy-MM-dd HH:mm:ss yyyy-MM-dd等样式
     * @return
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * string转date
     *
     * @param str
     * @param pattern yyyy-MM-dd HH:mm:ss   yyyy-MM-dd等样式
     * @return
     */
    public static Date string2Date(String str, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            Date date = formatter.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
