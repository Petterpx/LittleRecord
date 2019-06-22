package com.petterp.latte_core.net.rx;


import android.database.Observable;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author Petterp on 2019/4/20
 * Summary:
 * email：1509492795@qq.com
 */
public interface RxRestService {


    /**
     * get请求 @Qquery 注解在Get请求里面，以键值对的方式将我们传入的参数拼接到 url里面
     * Observable->可观察对象
     *
     * @param url
     * @param params
     * @return call
     */
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * Post请求,传入任何类型参数
     *
     * @param url
     * @param params
     * @return call
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Observable<String> delete(@Url String url, @FieldMap Map<String, Object> params);


    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

    /**
     * download 默认将文件一次下载到你的内存中，下载完成后，再写入文件中
     * 但当文件下载完之后，如果文件过大，就有机会造成内存泄漏，所以加上 @Streaming(一边下载一边写入，所以需要异步处理)
     *
     * @param url
     * @param params
     * @return respsebody
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * sdaldka
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Observable<String> uoload(@Url String url, @Part MultipartBody.Part file);
}
