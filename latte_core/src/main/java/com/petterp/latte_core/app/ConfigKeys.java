package com.petterp.latte_core.app;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:用枚举来做安全的单例(惰性单例),整个程序只会被初始化一次
 */
public enum ConfigKeys {
    API_HOST,       //配置网络请求域名
    APPLICATION_CONTEXT,        //全局上下文
    CONFIG_READY,       //控制初始化是否完成
    ICON,            //字体初始化
    LOADER_DELAYED,
    INTERCEPTOR,
    WE_CHAT_APP_ID,         //微信AppId
    WE_CHAT_APP_SECRET,     //微信Sercret
    BASEACTIVITY,            //全局Activity
    HANDLER,
    JAVASCRIPT_INTERFACE,
    LAUCHER_MODE            //是否重複开启倒计时页面
}
