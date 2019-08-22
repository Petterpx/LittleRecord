package com.petterp.latte_core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte_core.app.Latte;


/**
 * @author Petterp on 2019/4/20
 * Summary:
 * email：1509492795@qq.com
 */
public final class LatterPreference {
    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFERENCES = PreferenceManager.getDefaultSharedPreferences(Latte.getContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    public static void setAppProfice(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }


    /**
     * 是否第一次使用
     *
     * @param key
     * @param flag
     */
    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }

    /**
     * 是否登录
     *
     * @param flag
     */
    public static void setLoginMode(boolean flag) {
        getAppPreference().edit().putBoolean("login", flag).apply();
    }

    public static  boolean getLoginMode() {
        return getAppPreference().getBoolean("login", false);
    }

    /**
     * 设置用户key->账号唯一
     * @param id
     */
    public static void setUserId(String id){
        getAppPreference().edit().putString("userId", id).apply();
    }

    public static  String getUserId() {
        return getAppPreference().getString("userId", "");
    }

    public static void setFinderPaintf(boolean mode){
        getAppPreference().edit().putBoolean("finder",mode).apply();
    }
    public static boolean getFinderPaintf(){
        return getAppPreference().getBoolean("finder",false);
    }

}
