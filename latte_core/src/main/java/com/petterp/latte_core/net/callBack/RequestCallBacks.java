package com.petterp.latte_core.net.callBack;

import android.os.Handler;

import com.petterp.latte_core.ui.loader.LatteLoader;
import com.petterp.latte_core.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Petterp on 2019/4/18
 * Summary:
 * email：1509492795@qq.com
 */
public class RequestCallBacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    /**
     * 定义Handler对象时，定义为静态,避免一些内存泄露问题，比如Threadlocal的键key弱引用
     */
    private static final Handler HANDLER=new Handler();

    public RequestCallBacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=loaderStyle;
    }

    /**
     * 请求成功之后调用
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        //判断请求结果是否成功
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS!=null){
                    //回调接口
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{
            //请求结果失败
            if (ERROR!=null){
                //回调接口
                ERROR.onError(response.code(),response.message());
            }
        }
        stopLoading();
    }


    /**
     * 请求失败后调用
     * @param call
     * @param throwable
     */
    @Override
    public void onFailure(Call<String> call, Throwable throwable) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }
        //请求结束
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }
    private  void  stopLoading(){
        if (LOADER_STYLE != null) {
            //Hadnler 延迟发送
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }
}
