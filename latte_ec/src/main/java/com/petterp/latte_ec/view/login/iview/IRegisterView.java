package com.petterp.latte_ec.view.login.iview;

/**
 * @author by Petterp
 * @date 2019-07-31
 */
public interface IRegisterView {
    /**
     * 显示Loader
     */
    void showLoader();

    /**
     * 隐藏Loader
     */
    void hideLoader();


    /**
     * 设置倒计时
     * @param res
     */
    void setTvCode(String res);

    /**
     * 允许点击
     */
    void openClick();


    /**
     * 关闭点击
     */
    void closeClick();


    /**
     * 跳转设置具体用户信息
     */
    void onclickUserInfo();


    /**
     * 验证码错误
     */
    void codeError();
}
