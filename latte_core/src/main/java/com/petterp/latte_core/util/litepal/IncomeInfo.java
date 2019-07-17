package com.petterp.latte_core.util.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * 收入记录表
 * @author by Petterp
 * @date 2019-07-17
 */
public class IncomeInfo extends LitePalSupport {
    //插入时间->戳
    private long time;
    //相应金额
    private double money;
    //备注
    private String remark;
    //操作人
    private String name;

    public IncomeInfo(long time, double money, String remark, String name) {
        this.time = time;
        this.money = money;
        this.remark = remark;
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
