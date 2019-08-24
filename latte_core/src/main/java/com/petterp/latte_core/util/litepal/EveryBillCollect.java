package com.petterp.latte_core.util.litepal;


import com.petterp.latte_core.util.time.TimeUtils;

import org.litepal.crud.LitePalSupport;

/**
 * 每日账单汇总
 *
 * @author by Petterp
 * @date 2019-07-19
 */
public class EveryBillCollect extends LitePalSupport {

    private long id;

    //key+时间戳
    private String key;
    //时间戳
    private long longDate;

    //操作人
    private String name;

    //每日消费
    private float consume;

    //每日收入
    private float income;

    //当日添加次数
    private int sum;

    //年
    private String year;
    //月
    private String month;
    //日
    private String day;


    public EveryBillCollect(String key, long longDate, String name, float consume, float income, int sum) {
        this.key = key;
        this.longDate = longDate;
        this.name = name;
        this.consume = consume;
        this.income = income;
        this.sum = sum;
        setTimes(longDate);
    }

    public EveryBillCollect() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getLongDate() {
        return longDate;
    }

    public void setLongDate(long longDate) {
        this.longDate = longDate;
        setTimes(longDate);
    }

    private void setTimes(long longDate) {
        String[] times = TimeUtils.build().getYearMonthDays(longDate);
        this.year = times[0];
        this.month = times[1];
        this.day = times[2];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getConsume() {
        return consume;
    }

    public void setConsume(float consume) {
        this.consume = consume;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
