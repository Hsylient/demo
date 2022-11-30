package com.novax.ex.common.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Description: 时间格式化
 *
 * @Author shaoqiping
 * @Date 2018/9/27 16:25
 */
public class DateUtil {
    /** 一小时的毫秒值 */
    public static long ONE_HOUR = 1000L * 60 * 60;
    /** 一天的毫秒值 */
    public static long ONE_DAY = ONE_HOUR * 24;

    public final static String DEFAULT_DATE = "yyyy-MM-dd";

    public final static String DEFAULT_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String dateTo8String(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }

    public static String dateTo12String(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String dateTo14String(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 根据传入的日期返回指定格式的日期
     *
     * @param date
     * @param seqDateTime
     * @return
     */
    public static Date getDateTimeForSeq(Date date, String seqDateTime) {
        if (date == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(seqDateTime);
        String dateStr = dateFormat.format(date);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取指定日期的格式化字符串
     *
     * @param date
     * @param seqDateTime
     * @return
     */
    public static String getDateStringSeq(Date date, String seqDateTime) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(seqDateTime).format(date);
    }

    public static boolean check14DateStr(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(dateStr);
            return date != null;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * @Description 日期增加n天
     * @Author zhenghao
     * @Date 2019/11/26 14:33
     */
    public static Date addDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    /**
     *
     * Description: 日期增加n小时
     *
     * @author shaoqiping
     * @date 2020-09-23 14:41
     */
    public static Date addHour(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, n);
        return calendar.getTime();
    }

    /**
     * Description: 日期增加n秒
     *
     * @return java.util.Date
     * @author Mxc<xc@alicms.com>
     * @date 2022/1/21 14:52
     */
    public static Date addSecond(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, n);
        return calendar.getTime();
    }

    /**
     * Description: 日期增加n月，传入值为负值时，是减少n月
     * @param: [date]
     * @return: java.util.Date
     * @author: my.miao
     * @date: 2020/1/6 16:02
     */
    public static Date addMonth(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,  n);
        return calendar.getTime();
    }

    /**
     * @Description 将日期转为整点的毫秒值
     * @param date 要转换的日期
     * @return 转换后的毫秒值
     * @Author zhenghao
     * @Date 2019/11/12 16:31
     */
    public static long getHour(Date date) {
        return date.getTime() / ONE_HOUR * ONE_HOUR;
    }

    /**
     * @Description: 将日期转为整日
     * @param date 要转换的日期
     * @return: 转换后的毫秒值
     * @Author: zhenghao
     * @Date: 2019/11/12 16:31
     */
    public static Date date2Day(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Description: 获取本周的开始时间
     * @param: []
     * @return: java.util.Date
     * @author: my.miao
     * @date: 2019/12/27 17:26
     */
    public static Date getBeginDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取当前时间的前一天23：59：59
    public static Date getYesterdayEnd(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 0);
        todayEnd.add(Calendar.DATE, -1);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        return todayEnd.getTime();
    }

    //获取两个时间点间隔天数
    public static Long intervalTime(Date startTime,Date endTime){
        return Math.abs(endTime.getTime() - startTime.getTime())/(24*3600*1000);
    }

    /**
     * Description: 字符串时间转时间
     *
     * @param time 时间
     * @return java.util.Date
     * @author Mxc<xc@alicms.com>
     * @date 2022/1/21 15:05
     */
    public static Date stringToDate(String time){
        try {
            SimpleDateFormat simpleDateFormat;
            if(time.contains("T")){
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            }else{
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某个日期的结束时间，如：23:59:59
     *
     * @param days 可用加减，为0表示今天
     * @return
     * @throws ParseException
     */
    public static Date getFutureDayEndTime(int days) throws ParseException {
        Date futureDate = getFutureDay(days);
        DateFormat df = new SimpleDateFormat(DEFAULT_TIME);
        String targetDateStr = df.format(futureDate).substring(0, 10) + " 23:59:59";
        return df.parse(targetDateStr);
    }

    /**
     * 获取某个日期的开始时间，如：00:00:00
     *
     * @param days 可用加减，为0表示今天
     * @return
     * @throws ParseException
     */
    public static Date getFutureDayStartTime(int days) throws ParseException {
        Date futureDate = getFutureDay(days);
        DateFormat df = new SimpleDateFormat(DEFAULT_TIME);
        String targetDateStr = df.format(futureDate).substring(0, 10) + " 00:00:00";
        return df.parse(targetDateStr);
    }

    /**
     * 获取当前时间之后（之前）的日期
     *
     * @param days 天数，可以为负数
     * @return 指定日期格式的日期增加指定天数的日期
     */
    public static Date getFutureDay(int days) {
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            Date date = getDate();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取NTP时间
     *
     * @return
     */
    public static Date getDate() {
        return new Date();
    }

    public static void main(String[] args) throws  Exception {
        System.out.println(dateTo14String(getFutureDayStartTime(0)));
        System.out.println(dateTo14String(getFutureDayEndTime(0)));
    }
}
