package com.petterp.latte_core.util.time;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取时间工具类
 * @author by Petterp
 * @date 2019-07-19
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {

    private static final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat simpleDateFormatData=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 获取完整时间戳
     * @return
     */
    public static long getTimelong(){
        return System.currentTimeMillis();
    }

    /**
     * 获取年-月-日
     * @return
     */
    public static String getDate(){
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static String getDate(long time){
        return simpleDateFormat.format(time);
    }

    /**
     *
     * @param time
     * @return []
     */
    public static String[] getdate(long time){
        String[] date=simpleDateFormat.format(time).split("-");
        return date;
    }


    /**
     * 获取详细 年-月-日 时：分
     * @return
     */
    public static String getDateinfo(){
        return simpleDateFormatData.format(System.currentTimeMillis());
    }

    public static String getDateinfo(long time){
        return simpleDateFormatData.format(time);
    }

    /**
     * 获取 星期几
     * @return
     */
    public static String getday(){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
