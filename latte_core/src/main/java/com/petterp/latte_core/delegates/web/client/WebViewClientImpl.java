package com.petterp.latte_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.petterp.latte_core.delegates.IPageLoadListener;
import com.petterp.latte_core.delegates.web.WebDelegate;
import com.petterp.latte_core.delegates.web.route.Router;
import com.petterp.latte_core.ui.loader.LatteLoader;
import com.petterp.latte_core.util.log.LatteLogger;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * email：1509492795@qq.com
 */
public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener=null;
    private Handler handler=new Handler();

    public void setmIPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener=listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverriderUrlLoading",url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    //页面打开时，加载请求
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadEnd();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        },1000);
    }
}
