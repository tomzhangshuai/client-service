package com.wufanbao.api.oldclientservice.Tool;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    //判断选择的日期是否是今天
    public static boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    //判断选择的日期是否是本月
    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    private static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        System.out.println(date + "dateTime");
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //参数时间
        String param = sdf.format(date);
        //当前时间
        String now = sdf.format(new Date());
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    /**
     * 格式化时间
     *
     * @param date 需要格式化的时间
     * @return java.lang.String
     * @author Wang Zhiyuan
     * @date 2018/6/20
     */
    public static String format(Date date) {
        DateTime now = new DateTime();
        DateTime today_start = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
        DateTime today_end = today_start.plusDays(1);
        DateTime yesterday_start = today_start.minusDays(1);
        if (date.after(today_start.toDate()) && date.before(today_end.toDate())) {
            return String.format("今天 %s", new DateTime(date).toString("HH:mm"));
        } else if (date.after(yesterday_start.toDate()) && date.before(today_start.toDate())) {
            return String.format("昨天 %s", new DateTime(date).toString("HH:mm"));
        }
        return new DateTime(date).toString("yyyy-MM-dd HH:mm");
    }

    public static String formatDateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date formatDateToDate(String date, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    public static Date getCouponEndTime(int actieTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //+1今天的时间加一天
        calendar.add(Calendar.DAY_OF_MONTH, +actieTime);
        Date date = calendar.getTime();
        return date;
    }

}
