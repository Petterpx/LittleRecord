package com.petterp.latte_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

/**
 * @author Petterp on 2019/4/28
 * Summary:
 * email：1509492795@qq.com
 */
public class WebViewlinitalizer {
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public WebView createWebView(WebView webView){
        WebView.setWebContentsDebuggingEnabled(true);
        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //初始化WebSettings
        final WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
        final String ua=settings.getUserAgentString();
        //用于判断是否是我们的App才打开这个网页
        settings.setUserAgentString(ua+"Latte");
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);

        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        //使用默认
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        return webView;
    }
}
