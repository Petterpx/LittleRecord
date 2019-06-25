package com.petterp.latte_core.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Petterp on 2019/4/19
 * Summary:基础拦截器
 * 邮箱：1509492795@qq.com
 */
public abstract class Baseinterceptor implements Interceptor {



    @Override
    public abstract Response intercept(Chain chain) throws IOException;

    /**
     * //url参数对
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url=chain.request().url();
        //获取传入的url参数个数
        int size=url.querySize();
        //便于下面有序存储
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 通过key值获取value
     * @param chain
     * @param key
     * @return
     */
    protected  String getUrlParameters(Chain chain, String key){
        final Request request=chain.request();
        return request.url().queryParameter(key);
    }
    protected  LinkedHashMap<String,String> getBodyparameters(Chain chain){
        final FormBody formBody= (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        int size= formBody != null ? formBody.size() : 0;
        //从name方法通过下标获取key，通过value()方法获得value值
        for (int i=0;i<size;i++){
            params.put(formBody.name(i),formBody.value(i));
        }
        return params;
    }

    protected String getBodyparameters(Chain chain, String key){
        return getBodyparameters(chain).get(key);
    }
}
