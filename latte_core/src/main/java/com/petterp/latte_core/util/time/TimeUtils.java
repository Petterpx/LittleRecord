package com.petterp.latte_core.util.time;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取时间工具类
 *
 * @author by Petterp
 * @date 2019-07-19
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {
    //
    //周几
    private final SimpleDateFormat FORMAT_DAY;
    //年
    private final SimpleDateFormat FORMAT_YEAR;
    //年-月
    private final SimpleDateFormat FORMAT_YEAR_MONTH;
    //月-日
    private final SimpleDateFormat FORMAT_MONTH_DAY;
    //年-月-日
    private final SimpleDateFormat FORMAT_YEAR_MONTH_DAY;
    //具体时间
    private final SimpleDateFormat FORMAT_TIME_INFO;


    private TimeUtils() {
        FORMAT_YEAR = new SimpleDateFormat("yyyy");
        FORMAT_DAY = new SimpleDateFormat("EEEE");
        FORMAT_YEAR_MONTH = new SimpleDateFormat("yyyy-MM");
        FORMAT_YEAR_MONTH_DAY = new SimpleDateFormat("yyyy-MM-dd");
        FORMAT_TIME_INFO = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        FORMAT_MONTH_DAY = new SimpleDateFormat("MM-dd");
    }

    private static class Client {
        private static final TimeUtils timeUtils = new TimeUtils();
    }

    public static TimeUtils build() {
        return Client.timeUtils;
    }

    /**
     * 获取完整时间戳
     *
     * @return
     */
    public long getTimelong() {
        return SystemClock.now();
    }

    /**
     * 获取年-月-日
     *
     * @return
     */
    public String getYearMonthDay() {
        return FORMAT_YEAR_MONTH_DAY.format(SystemClock.now());
    }

    public String getYearMonthDay(long time) {
        return FORMAT_YEAR_MONTH_DAY.format(time);
    }


    /**
     * 获取时间戳key
     *
     * @return
     */
    public String getLongTimekey() {
        Date date = null;
        try {
            date = FORMAT_YEAR_MONTH_DAY.parse(getYearMonthDay());
            return "key" + date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取当前时间组
     *
     * @param time
     * @return []
     */
    public String[] getYearMonthDays(long time) {
        return FORMAT_YEAR_MONTH_DAY.format(time).split("-");
    }

    public String[] getYearMonthDays() {
        return FORMAT_YEAR_MONTH_DAY.format(SystemClock.now()).split("-");
    }


    /**
     * 获取当前年
     *
     * @return
     */
    public String getYear() {
        return FORMAT_YEAR.format(SystemClock.now());
    }

    public String getYear(long time) {
        return FORMAT_YEAR.format(time);
    }

    public long getYear(String time) {
        try {
            return FORMAT_YEAR.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取当前年-月
     *
     * @return
     */
    public String getYearMonth() {
        return FORMAT_YEAR_MONTH.format(SystemClock.now());
    }

    public String getYearMonth(long time) {
        return FORMAT_YEAR_MONTH.format(time);
    }

    /**
     * 判断year-month 是否与今天相等
     *
     * @return
     */
    public boolean isMonth(long time) {
        if (getYearMonth().equals(FORMAT_YEAR_MONTH.format(time))) {
            return true;
        }
        return false;
    }

    /**
     * 判断year-month 是否相等
     *
     * @return
     */
    public boolean isMonth(long time, String res) {
        if (res.equals(FORMAT_YEAR_MONTH.format(time))) {
            return true;
        }
        return false;
    }


    /**
     * 获取详细 年-月-日 时：分
     *
     * @return
     */
    public String getDateinfo() {
        return FORMAT_TIME_INFO.format(SystemClock.now());
    }

    public String getDateinfo(long time) {
        return FORMAT_TIME_INFO.format(time);
    }

    /**
     * 获取 星期几
     *
     * @return
     */
    public String getday() {
        return FORMAT_DAY.format(SystemClock.now());
    }

    public String getday(long time) {
        return FORMAT_DAY.format(time);
    }

    public String getMonthDay(){
        return FORMAT_MONTH_DAY.format(SystemClock.now());
    }

    public String getMonthDay(long time){
        return FORMAT_MONTH_DAY.format(time);
    }

}
