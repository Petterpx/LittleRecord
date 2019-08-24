package com.petterp.latte_core.util.litepal;


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
    private float money;
    //备注
    private String remark;
    //选择的条目
    private String kind;
    //消费还是支出
    private String category;
    private String kindIcon;

    public BillInfo( String key, long longDate, float money, String remark, String kind, String category, String kindIcon) {
        this.key = key;
        this.longDate = longDate;
        this.money = money;
        this.remark = remark;
        this.kind = kind;
        this.category = category;
        this.kindIcon = kindIcon;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getKindIcon() {
        return kindIcon;
    }

    public void setKindIcon(String kindIcon) {
        this.kindIcon = kindIcon;
    }
}
