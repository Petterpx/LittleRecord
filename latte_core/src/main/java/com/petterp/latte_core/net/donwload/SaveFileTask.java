package com.petterp.latte_core.net.donwload;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.net.callBack.IRequest;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * @author Petterp on 2019/4/18
 * Summary:下载的异步框架
 * email：1509492795@qq.com
 */
//三个泛型参数，Object类型，无进度，返回它的File
public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {

        //文件名
        String downloadDir= (String) params[0];
        String extension= (String) params[1];
        //请求体
        final ResponseBody body= (ResponseBody) params[2];
        final String name= (String) params[3];
        //得到输入流
        final InputStream is=body.byteStream();
        if (downloadDir==null||downloadDir.equals("")){
            //指定下载目录。。下载目录一般不可变
            downloadDir="down_loads";
        }
        //如果有默认的选项可以在这添加回调
        if (extension==null||extension.equals("")){
            extension="";
        }
        if (name==null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else{
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }


    /**
     * //执行完异步回到主线程的操作
     * @param file
     */
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoIstallApk(file);
    }


    /**
     *  //如果下载的是apk,单独安装apk
     * @param file
     */
    private void autoIstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent instatll =new Intent();
            //新起一个栈
            instatll.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            instatll.setAction(Intent.ACTION_VIEW);
            instatll.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getContext().startActivity(instatll);
        }
    }
}
