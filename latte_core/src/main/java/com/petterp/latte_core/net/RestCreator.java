package com.petterp.latte_core.net;

import com.petterp.latte_core.app.ConfigKeys;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Petterp on 2019/4/17
 * Summary:内部类holder单例模式
 * email：1509492795@qq.com
 */
public class RestCreator {

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static final WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    //创造方法
    public static RestService getRestServerce() {
        return RestServiceHolder.REST_SERVICE;
    }


    /**
     * 全局只有一个实例，所以直接统一创建
     * 这里得到了我们传入的 BASER_url ，也就是在 example中的withApiHost 传入的
     * RETROFIT_CLIENT 简化版建造者模式，网络请求->惰性初始Okhttp->返回String类型
     */
    private static final class RetrfitHolder {
        private static final String BASEE_URL = (String) Latte.getConfiguration().get(ConfigKeys.API_HOST.name());
        //简化版建造者模式，网络请求->惰性初始Okhttp->返回String类型
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASEE_URL)
                .client(OKhttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())      //返回String类型
                .build();
    }

    /**
     * OkHttp 的惰性初始化
     */
    private static final class OKhttpHolder {

        private static final int TIME_OUT = 60;
        //优化效率，避免多次创建
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        //通过循环的方式将拦截器传入OkHttp
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS
                        ) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }


        //建造者模式
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                //传入时间，以秒为单位
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKhttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    //RestService
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrfitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    //RestService
    private static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


    //RestService
    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE =
                RetrfitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    //RestService
    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }



}
