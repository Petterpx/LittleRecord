package com.petterp.latte_core.net.callBack;

/**
 * @author Petterp on 2019/4/18
 * Summary:加载的时候的加载圈，转圈
 * 邮箱：1509492795@qq.com
 */
public interface IRequest {
    void onRequestStart();
    void onRequestEnd();

}
