package com.xandone.yblog.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2020/9/21 14:35
 * description：
 */
public class DateUtils {

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat NO_HOUR_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String date2String(final Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }

    public static String date2String(final Date date, final DateFormat format) {
        return format.format(date);
    }

    public static Date string2Date(final String time) {
        try {
            return DEFAULT_FORMAT.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date string2Date(final String time, final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDateString(String time, final DateFormat format) {
        return date2String(string2Date(time, format), format);
    }

    public static String getNextYear(String startDate) {
        return getNextYear(startDate, 1);
    }

    public static String getNextYear(String startDate, int addYear) {
        try {
            Date date = DateUtils.string2Date(startDate, DateUtils.NO_HOUR_FORMAT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, addYear);
            return DateUtils.date2String(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取下一年的1月1日
     *
     * @return
     */
    public static String getNextHeadYear() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int year = calendar.getWeekYear() + 1;
            calendar.set(year, Calendar.JANUARY, 1);
            return DateUtils.date2String(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
