package com.petterp.latte_core.util.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * 收支情况表-详细
 * @author by Petterp
 * @date 2019-07-17
 */
public class BillInfo extends LitePalSupport {
    //插入时间->戳
    private long time;

    //插入时间-日
    private String timeday;

    //相应金额
    private double money;
    //备注
    private String remark;
    //操作人
    private String name;
    //类别
    private String kind;
    //消费还是支出
    private String bill;

    public BillInfo(long time, String timeday, double money, String remark, String name, String kind, String bill) {
        this.time = time;
        this.timeday = timeday;
        this.money = money;
        this.remark = remark;
        this.name = name;
        this.kind = kind;
        this.bill = bill;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTimeday() {
        return timeday;
    }

    public void setTimeday(String timeday) {
        this.timeday = timeday;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
