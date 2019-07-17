package com.petterp.latte_core.util.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * 用户信息表
 * @author by Petterp
 * @date 2019-07-17
 */
public class UserInfo extends LitePalSupport {
    private String name;
    private String pswd;
    private String phone;
    private String qq;
    private String emial;

    public UserInfo(String name, String pswd, String phone, String qq) {
        this.name = name;
        this.pswd = pswd;
        this.phone = phone;
        this.qq = qq;
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
}
