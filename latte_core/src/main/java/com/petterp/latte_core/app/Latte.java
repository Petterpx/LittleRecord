package com.petterp.latte_core.app;

import android.content.Context;
import android.os.Handler;

import com.petterp.latte_core.mvp.base.BaseActivity;

import java.util.HashMap;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:对外的一个工具类
 */
public final class Latte {
    /**
     *  建造者
     * @param context
     * @return
     */
    public static Configurator init(Context context){
        getConfiguration().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static HashMap<Object,Object> getConfiguration(){
        return Configurator.getInstance().getLatteConfigs();
    }
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    /**
     * 返回全局Context
     * @return
     */
    public static Context getContext(){
        return (Context) getConfiguration().get(ConfigKeys.APPLICATION_CONTEXT);
    }

    /**
     * 返回全局Activity
     * @return
     */
    public static BaseActivity getBaseActivity(){
        return (BaseActivity) getConfiguration().get(ConfigKeys.BASEACTIVITY);
    }

    /**
     * 返回全局Handler
     * @return
     */
    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    /**
     * 获取是否开启多次开启轮播图
     * @return
     */
    public static boolean getLauncherMode(){
        return getConfiguration(ConfigKeys.LAUCHER_MODE);
    }
}
