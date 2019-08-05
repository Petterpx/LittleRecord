package com.petterp.latte_core.app;

/**
 * @author Petterp on 2019/4/21
 * Summary:用户是否登录信息回调
 * email：1509492795@qq.com
 */
public interface IUserCheker {
    void onSignIn();

    void onNotSoignIn();
}
