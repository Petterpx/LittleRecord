package com.petterp.latte_core.net;

import android.content.Context;

import com.petterp.latte_core.net.callBack.IError;
import com.petterp.latte_core.net.callBack.IFailure;
import com.petterp.latte_core.net.callBack.IRequest;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_core.net.callBack.RequestCallBacks;
import com.petterp.latte_core.net.donwload.DownloadHandler;
import com.petterp.latte_core.ui.loader.LatteLoader;
import com.petterp.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * auther: Petterp on 2019/4/17
 * Summary: 网络请求框架——建造者模式
 *
 * @author Pettepr
 */
public class RestClient {
    //保证每次传值的原子性，不可被更改
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    //请求体
    private final RequestBody BODY;
    private final File FILE;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    //转圈动画
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      String downloadDir,
                      String extenSion,
                      String name,
                      Context context,
                      LoaderStyle loaderStyle

    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE=file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.DOWNLOAD_DIR=downloadDir;
        this.EXTENSION=extenSion;
        this.NAME=name;
    }

    /**
     * 建造者
     *
     * @return RestClientBuilder
     */
    public static RestClientBulder builder() {
        return new RestClientBulder();
    }

    /**
     * 请求开始时的做法
     * @param method
     */
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestServerce();
        Call<String> call = null;
        if (REQUEST != null) {
            //开始转圈
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call=service.putRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody= RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body= MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call=RestCreator.getRestServerce().uoload(URL,body);
                break;
            default:
                break;
        }
        if (call != null) {
            //异步线程
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY==null){
            request(HttpMethod.POST);
        }else{
            //是否有元素
            if (!PARAMS.isEmpty()){
                throw  new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY==null){
            request(HttpMethod.PUT);
        }else{
            //是否有元素
            if (!PARAMS.isEmpty()){
                throw  new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
    public final void upload(){
        request(HttpMethod.UPLOAD);
    }
    public final void download(){
        //NAME完整的文件名，文件名DOWNLOAD_DIR,二者一般只传其一
        new DownloadHandler(URL,REQUEST,SUCCESS,FAILURE,ERROR,DOWNLOAD_DIR,EXTENSION,NAME).handleDownload();
    }

}
