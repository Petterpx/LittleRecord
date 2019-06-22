package com.petterp.latte_core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.HashMap;

/**
 * @author Petterp on 2019/4/18
 * Summary:以缓存的方式创建 Loader，避免每次使用时都去反射一次。因为该框架默认使用的是反射创建。
 * email：1509492795@qq.com
 */
public final class LoaderCreator {
    //这块既然是缓存作用，不音再使用 WeekHashMap
    private static final HashMap<String, Indicator> LOADING_MAP=new HashMap<>();

    /** 调用的时候传入类名，及Contenxt。
     * 如果第一次则会通过反射去返回 Indicator对象，否则直接返回，然后通过 setIndicator()的方法设置
     * @param type
     * @param context
     * @return avLoadingIndicatorView
     */
    //调用的时候传入类名，及Contenxt
    static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        //缓存作用，只有第一次会去反射创建
        if (LOADING_MAP.get(type)==null){
            final Indicator indicator=getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**传入类名
     * @param name
     * @return indicator
     */
    private static Indicator getIndicator(String name){
        if (name==null||name.isEmpty()){
            return null;
        }
        //省内存
        final StringBuilder drawbleClassName =new StringBuilder();
        //如果包含"."，意味着传入整个类名
        if (!name.contains(".")){
            //反射获得类名
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();

            drawbleClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawbleClassName.append(name);
        try {
            //反射拿到Clas对象,确保类已经被加载
            final Class<?> drawleClass=Class.forName(drawbleClassName.toString());
            //返回 Indicator对象
            return (Indicator) drawleClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
