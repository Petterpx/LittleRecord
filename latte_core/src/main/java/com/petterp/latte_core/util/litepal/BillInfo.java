package com.petterp.latte_core.util.litepal;

import com.petterp.latte_core.util.time.TimeUtils;

import org.litepal.crud.LitePalSupport;

/**
 * 收支情况表-详细
 * @author by Petterp
 * @date 2019-07-17
 */
public class BillInfo extends LitePalSupport {
    private long id;
    //key->对应每日key
    private String key;
    //插入时间->戳
    private long longDate;
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

    public BillInfo(String key, long longDate, double money, String remark, String name, String kind, String category) {
        this.key = key;
        this.longDate = longDate;
        this.money = money;
        this.remark = remark;
        this.name = name;
        this.kind = kind;
        this.category = category;
    }
    public  BillInfo(){

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
