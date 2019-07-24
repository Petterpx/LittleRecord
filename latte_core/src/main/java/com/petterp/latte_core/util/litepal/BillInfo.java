package com.petterp.latte_core.util.litepal;

import com.petterp.latte_core.util.time.TimeUtils;

import org.litepal.crud.LitePalSupport;

/**
 * 收支情况表-详细
 * @author by Petterp
 * @date 2019-07-17
 */
public class BillInfo extends LitePalSupport {
    //插入时间->戳
    private long longDate;
    //插入时间 年-月-日
    private String dateRes;
    //相应金额
    private double money;
    //备注
    private String remark;
    //操作人
    private String name;
    //选择的条目
    private String kind;
    //消费还是支出
    private String category;

    public BillInfo(long longDate, String dateRes, double money, String remark, String name, String kind, String category) {
        this.longDate = longDate;
        this.dateRes = dateRes;
        this.money = money;
        this.remark = remark;
        this.name = name;
        this.kind = kind;
        this.category = category;
    }

    public long getLongDate() {
        return longDate;
    }

    public void setLongDate(long longDate) {
        this.longDate = longDate;
    }

    public String getDateRes() {
        return dateRes;
    }

    public void setDateRes(String dateRes) {
        this.dateRes = dateRes;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
