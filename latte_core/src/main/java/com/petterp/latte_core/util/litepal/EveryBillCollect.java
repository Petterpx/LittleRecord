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
    private String dateRes;

    //时间戳
    private long longDate;

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

    public EveryBillCollect(String dateRes, long longDate, String name, Double consume, Double income, int sum) {
        this.dateRes = dateRes;
        this.longDate = longDate;
        this.name = name;
        this.consume = consume;
        this.income = income;
        this.sum = sum;
    }

    public String getDateRes() {
        return dateRes;
    }

    public void setDateRes(String dateRes) {
        this.dateRes = dateRes;
    }

    public long getLongDate() {
        return longDate;
    }

    public void setLongDate(long longDate) {
        this.longDate = longDate;
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
