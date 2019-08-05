package com.petterp.latte_core.util.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * 用户信息表
 * @author by Petterp
 * @date 2019-07-17
 */
public class UserInfo extends LitePalSupport {
    private long id;
    private String key;
    private String name;
    private String pswd;
    private String phone;
    private String qq;
    private String emial;
    private String sex;
    private String iconUrl;

    public UserInfo(String key, String name, String pswd, String phone, String qq, String emial, String sex, String iconUrl) {
        this.key = key;
        this.name = name;
        this.pswd = pswd;
        this.phone = phone;
        this.qq = qq;
        this.emial = emial;
        this.sex = sex;
        this.iconUrl = iconUrl;
    }

    public UserInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
