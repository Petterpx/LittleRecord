package com.petterp.latte_ec.main.login;

import com.petterp.latte_ec.main.login.imodel.IRegisterModel;

import cn.smssdk.SMSSDK;

/**
 * @author by Petterp
 * @date 2019-07-31
 */
public class IRegisterImpl implements IRegisterModel {

    private String phone;
    private String code;
    private String country = "86";

    /**
     * * // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
     *
     * @param phone
     */
    @Override
    public void createUser(String phone) {
        this.phone = phone;
        SMSSDK.getVerificationCode(country, phone);
    }


    /**
     * // 提交验证码，其中的code表示验证码，如“1357”
     *
     * @param code
     */
    @Override
    public void createCode(String code) {
        this.code = code;
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    @Override
    public String getPhone() {
        return phone;
    }

}
