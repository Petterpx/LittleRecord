package com.petterp.latte_core.net.rx;

import android.content.Context;

import com.petterp.latte_core.net.RestCreator;
import com.petterp.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Petterp on 2019/4/18
 * Summary:回调接口
 * 邮箱：1509492795@qq.com
 */
public class RxRestClientBulder {
    //如果是建造者模式，不能用fianl赋值
    private String mUrl = null;
    public static Map<String, Object> Params = RestCreator.getParams();
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mfle = null;

    //仅允许同包的去创建它
    RxRestClientBulder() {
    }

    public final RxRestClientBulder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBulder params(WeakHashMap<String, Object> parms) {
        Params = parms;
        return this;
    }


    public final RxRestClientBulder file(File file) {
        this.mfle = file;
        return this;
    }
    /**
     * //File可以地址也可以传别的
     * @param file
     * @return
     */
    public final RxRestClientBulder file(String file) {
        this.mfle = new File(file);
        return this;
    }

    /**
     * //传入键对值方式
     * @param key
     * @param value
     * @return
     */
    public final RxRestClientBulder params(String key, Object value) {
        Params.put(key, value);
        return this;
    }

    /**
     *  传入原始数据
     * @param raw
     * @return
     */
    public final RxRestClientBulder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBulder loader(Context context) {
        this.mContext = context;
        //未使用默认类型
        this.mLoaderStyle = LoaderStyle.BallClipRotateIndicator;
        return this;
    }


    public final RxRestClient build() {
        return new RxRestClient(mUrl, Params,mBody,mfle,mContext, mLoaderStyle);
    }
}
