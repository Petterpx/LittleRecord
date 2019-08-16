package com.petterp.latte_core.util.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * 用户信息表
 * @author by Petterp
 * @date 2019-07-17
 */
public class UserInfo extends LitePalSupport {

    private long id;
    //账号
    private String account;
    //用户名
    private String name;
    //密码
    private String pswd;
    //性别
    private String sex;
    //头像url
    private String iconUrl;
    //账号类型,0为注册用户，1为QQ登录
    private String accountMode;

    public UserInfo( String key, String name, String pswd, String account, String sex, String iconUrl, String accountMode) {
        this.name = name;
        this.pswd = pswd;
        this.account = account;
        this.sex = sex;
        this.iconUrl = iconUrl;
        this.accountMode = accountMode;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getAccountMode() {
        return accountMode;
    }

    public void setAccountMode(String accountMode) {
        this.accountMode = accountMode;
    }

    public long getId() {
        return id;
    }
}
