package com.petterp.latte_core.delegates.web;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.petterp.latte_core.delegates.IPageLoadListener;
import com.petterp.latte_core.delegates.web.chromeclient.WebChromeClientImpl;
import com.petterp.latte_core.delegates.web.client.WebViewClientImpl;
import com.petterp.latte_core.delegates.web.route.RouteKeys;
import com.petterp.latte_core.delegates.web.route.Router;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * email：1509492795@qq.com
 */
public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener=null;
    public void setmIPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener=listener;
    }


    //自己创建自己
    public static WebDelegateImpl create(String url){
        final Bundle args=new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate=new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public Object setLayout() {
        return getmWebView();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        if (getUrl()!=null){
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public View getToolbar() {
        return null;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewlinitalizer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client=new WebViewClientImpl(this);
        client.setmIPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

}
