package com.wufanbao.api.clientservice.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    /**
     * 字符串转换为日期
     *
     * @param string
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String string, String pattern) throws ParseException {
        if (string == null || string.length() <= 0) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(string);
        return date;
    }

    public static Date StringToDate(String string) throws ParseException {
        return StringToDate(string, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期转换为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String DateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String string = simpleDateFormat.format(date);
        return string;
    }

    public static String DateToString(Date date) {
        return DateToString(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 判断当前时间是否在时间范围里
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取n分钟之后的的时间
     *
     * @param n
     * @return
     */
    public static Date getAfterTime(int n) {
        Date date = new Date();
        date.setTime(date.getTime() + n * 60 * 1000);
        return date;
    }

    public static Date getAfterTime(Date date, int n) {
        date.setTime(date.getTime() + n * 60 * 1000);
        return date;
    }

    public static Date getAfterDay(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, +n);
        Date date = calendar.getTime();
        return date;
    }

    //获取n月后的时间
    public static Date getAfterMonth(int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, n);
        return c.getTime();
    }

    //获取n月前的时间
    public static Date getBeforeMonth(int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -n);
        return c.getTime();
    }

    //获取时间分钟差
    public static long getDiffMinutes(Date date1, Date date2) {
        try {
            long diff = date1.getTime() - date2.getTime();//这样得到的差值是微秒级别
            return diff / (1000 * 60);
//            long days = diff / (1000 * 60 * 60 * 24);
//            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
//            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");
        } catch (Exception e) {
            return 0;
        }
    }
    //获取秒分钟差
    public static long getDiffsecond(int date1, Date date2) {
        try {
            long diff = date1*1000 - date2.getTime();//这样得到的差值是微秒级别
            return diff / 1000;
//            long days = diff / (1000 * 60 * 60 * 24);
//            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
//            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");
        } catch (Exception e) {
            return 0;
        }
    }

    //获取时间天数差
    public static long getDiffDay(Date date1, Date date2) {
        try {
            long diff = date1.getTime() - date2.getTime();//这样得到的差值是微秒级别
            return diff / (1000 * 60 * 60 * 24);
//            long days = diff / (1000 * 60 * 60 * 24);
//            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
//            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");
        } catch (Exception e) {
            return 0;
        }
    }

    //判断是否是当天
    public static boolean isToday(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        if (fmt.format(date).toString().equals(fmt.format(new Date()).toString())) {//格式化为相同格式
            return true;
        } else {
            return false;
        }
    }

    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

}
