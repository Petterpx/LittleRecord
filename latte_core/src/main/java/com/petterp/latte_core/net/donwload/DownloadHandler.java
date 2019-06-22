package com.petterp.latte_core.net.donwload;

import android.os.AsyncTask;

import com.petterp.latte_core.net.RestCreator;
import com.petterp.latte_core.net.callBack.IError;
import com.petterp.latte_core.net.callBack.IFailure;
import com.petterp.latte_core.net.callBack.IRequest;
import com.petterp.latte_core.net.callBack.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Petterp on 2019/4/18
 * Summary:
 * email：1509492795@qq.com
 */
public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    //下载的目录
    private final IRequest REQUEST;
    //成功
    private final ISuccess SUCCESS;
    //失败
    private final IFailure FAILURE;
    //出错
    private final IError ERROR;

    //文件名
    private final String DOWNLOAD_DIR;
    //完整的文件名,二者一般只传其一
    private final String NAME;


    //后缀
    private final String EXTENSION;



    public DownloadHandler(String url, IRequest request, ISuccess success, IFailure failure, IError error, String download_dir, String extension, String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handleDownload(){
        if (REQUEST != null) {
            //开始下载
            REQUEST.onRequestStart();
        }
        RestCreator.getRestServerce().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       if (response.isSuccessful()){
                           //拿到请求体
                           final ResponseBody responseBody=response.body();

                           final  SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                           //以线程池方式执行
                           task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,response,NAME);

                           //这里一定要注意判断，否则文件下载不全
                           if (task.isCancelled()){
                               if (REQUEST!=null){
                                   REQUEST.onRequestEnd();
                               }
                           }
                       }else{
                           //进行“错误”的回调
                           if (ERROR!=null){
                               ERROR.onError(response.code(),response.message());
                           }
                       }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE!=null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }


}
