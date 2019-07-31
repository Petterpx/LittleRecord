package com.petterp.latte_ec.model.login.imodel;

/**
 * 创建新账号
 *
 * @author by Petterp
 * @date 2019-07-31
 */
public interface IRegisterModel {
    /**
     * 手机号码
     * @param res
     */
    void createUser(String res);

    /**
     * 获取验证码
     * @return
     */
    String getCode();
}
