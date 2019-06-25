package com.petterp.latte_core.net;

import android.content.Context;

import com.petterp.latte_core.net.callBack.IError;
import com.petterp.latte_core.net.callBack.IFailure;
import com.petterp.latte_core.net.callBack.IRequest;
import com.petterp.latte_core.net.callBack.ISuccess;
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
public class RestClientBulder {
    //如果是建造者模式，不能用fianl赋值
    private String mUrl = null;
    public static Map<String, Object> Params = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mIsuccess = null;
    private IFailure mIFailure = null;
    private IError mIerror = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mfle = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    //仅允许同包的去创建它
    RestClientBulder() {
    }

    public final RestClientBulder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBulder params(WeakHashMap<String, Object> parms) {
        Params = parms;
        return this;
    }


    public final RestClientBulder file(File file) {
        this.mfle = file;
        return this;
    }


    /**
     * //File可以地址也可以传别的
     * @param file
     * @return
     */
    public final RestClientBulder file(String file) {
        this.mfle = new File(file);
        return this;
    }


    /**
     * //传入键对值方式
     * @param key
     * @param value
     * @return
     */
    public final RestClientBulder params(String key, Object value) {
        Params.put(key, value);
        return this;
    }


    /**
     *  传入原始数据
     * @param raw
     * @return
     */
    public final RestClientBulder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBulder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }


    /**
     * 成功
     * @param iSuccess
     * @return
     */
    public final RestClientBulder success(ISuccess iSuccess) {
        this.mIsuccess = iSuccess;
        return this;
    }

    public final RestClientBulder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }


    /**
     * 文件下载存放位置
     * @param idir
     * @return
     */
    public final RestClientBulder dir(String idir) {
        this.mDownloadDir =idir;
        return this;
    }


    public final RestClientBulder extension(String  ietension) {
        this.mExtension = ietension;
        return this;
    }

    public final RestClientBulder name(String  iname) {
        this.mName = iname;
        return this;
    }

    public final RestClientBulder error(IError ierror) {
        this.mIerror = ierror;
        return this;
    }



    public final RestClientBulder loader(Context context) {
        this.mContext = context;
        //未使用默认类型
        this.mLoaderStyle = LoaderStyle.BallClipRotateIndicator;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, Params, mIRequest, mIsuccess, mIFailure, mIerror, mBody, mfle,mDownloadDir,mExtension,mName,mContext, mLoaderStyle);
    }
}
