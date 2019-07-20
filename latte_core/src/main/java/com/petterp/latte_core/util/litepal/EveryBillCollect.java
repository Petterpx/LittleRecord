package com.petterp.latte_core.util.litepal;

import com.petterp.latte_core.util.time.TimeUtils;

import org.litepal.crud.LitePalSupport;

/**
 * 每日账单汇总
 * @author by Petterp
 * @date 2019-07-19
 */
public class EveryBillCollect extends LitePalSupport {
    //只保留 年-月-日
    private String dateinfo;

    //周几
    private String day;

    private String dateMonth;

    private long datetime;

    //操作人
    private String name;

    //每日消费
    private Double consume;

    //每日收入
    private Double income;

    //今日添加次数
    private int sum;

    public EveryBillCollect(){

    }

    public EveryBillCollect(long datetime, String name, Double consume, Double income, int sum) {
        this.dateinfo = TimeUtils.build().getDate(datetime);
        this.day = TimeUtils.build().getday(datetime);
        this.dateMonth=TimeUtils.build().getYearMonth(datetime);
        this.datetime = datetime;
        this.name = name;
        this.consume = consume;
        this.income = income;
        this.sum = sum;
    }

    public String getDateinfo() {
        return dateinfo;
    }

    public void setDateinfo(String dateinfo) {
        this.dateinfo = dateinfo;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getConsume() {
        return consume;
    }

    public void setConsume(Double consume) {
        this.consume = consume;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
