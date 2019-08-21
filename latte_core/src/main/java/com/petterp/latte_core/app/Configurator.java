package com.petterp.latte_core.app;


import android.os.Handler;

import androidx.annotation.NonNull;
import com.example.rxretifoit.net.RestUrlInfo;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.petterp.latte_core.mvp.base.BaseActivity;


import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:配置文件的存储与获取
 */
public class Configurator {
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    /**
     * 静态内部类单例模式，多线程安全
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        //初始化AndroidUtilsCode
        initIcons();
        //配置项完成
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    /**
     * 静态单例
     * @return
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 存储配置信息的键值对
     * @return
     */
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * api
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        RestUrlInfo.setURL(host);
        return this;
    }

    /**
     * 检查配置项是否完成
     * //如果配置未完成，抛出运行时异常
     * if (!isReady){
     * throw  new RuntimeException("Configuration is not ready,call configure");
     * }
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        //如果配置未完成，抛出运行时异常
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 获取相应的信息
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    /**
     * Icon
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initialize = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initialize.with(ICONS.get(i));
            }
        }
    }

    /**
     * 字体封装
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 一个拦截器
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 配合拦截器使用
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 转圈时间
     * @param delayed
     * @return
     */
    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 微信ID
     * @param appId
     * @return
     */
    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    /**
     * 微信AppSecret
     * @param appSecret
     * @return
     */
    public final Configurator withWeChatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    /**
     * 全局Activity
     * @param activity
     * @return
     */
    public final Configurator withBaseActivity(BaseActivity activity) {
        LATTE_CONFIGS.put(ConfigKeys.BASEACTIVITY, activity);
        return this;
    }

    /**
     * 全局Handler
     * @param handler
     * @return
     */
    public final Configurator withHandler(Handler handler) {
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, handler);
        return this;
    }


    /**
     * WebView注入
     * @param name
     * @return
     */
    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }


    /**
     * 是否每次启动显示倒计时页面
     * @param mode
     * @return
     */
    public Configurator withLaucherMode(boolean mode){
        LATTE_CONFIGS.put(ConfigKeys.LAUCHER_MODE,mode);
        return this;
    }

}
