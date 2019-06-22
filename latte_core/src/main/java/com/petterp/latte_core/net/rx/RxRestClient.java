package com.petterp.latte_core.net.rx;

import android.content.Context;
import android.database.Observable;

import com.petterp.latte_core.net.HttpMethod;
import com.petterp.latte_core.net.RestCreator;
import com.petterp.latte_core.ui.loader.LatteLoader;
import com.petterp.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * auther: Petterp on 2019/4/17
 * Summary: 网络请求框架——建造者模式
 *
 * @author Pettepr
 */
public class RxRestClient {
    //保证每次传值的原子性，不可被更改
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    //请求体
    private final RequestBody BODY;
    private final File FILE;
    //转圈动画
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle

    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    /**
     * 建造者
     *
     * @return RestClientBuilder
     */
    public static RxRestClientBulder builder() {
        return new RxRestClientBulder();
    }

    /**
     * 请求开始时的做法
     *
     * @param method
     */
    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().uoload(URL, body);
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            //是否有元素
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            //是否有元素
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        //NAME完整的文件名，文件名DOWNLOAD_DIR,二者一般只传其一
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }


}
