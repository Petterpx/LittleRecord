package com.petterp.latte_core.net.interceptors;


import androidx.annotation.RawRes;

import com.petterp.latte_core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Petterp on 2019/4/19
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class DebugInterceptor extends Baseinterceptor {

    //模拟Url
    private final  String DEBUG_URL;
    private final int  DEBUG_RAW_ID;

    public DebugInterceptor(String url, int rawId) {
        this.DEBUG_URL = url;
        this.DEBUG_RAW_ID = rawId;
    }

    private Response getRespnse(Chain chain, String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }
    //@RawRes 必须在R文件下注册的id
    private Response debugResponses(Chain chain, @RawRes int rawId){
        final String json= FileUtil.getRawFile(rawId);
        return getRespnse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final  String url=chain.request().url().toString();
        //如果url包含我们需要的关键字,则返回我们指定的数据，否则原样返回
        if (url.contains(DEBUG_URL)){
            return debugResponses(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
