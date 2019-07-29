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
    private Double consume;

    //每日收入
    private Double income;

    //当日添加次数
    private int sum;

    public EveryBillCollect(String key, long longDate, String name, Double consume, Double income, int sum) {
        this.key = key;
        this.longDate = longDate;
        this.name = name;
        this.consume = consume;
        this.income = income;
        this.sum = sum;
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
