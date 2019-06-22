package com.petterp.latte_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.petterp.latte_core.app.ConfigKeys;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * email：1509492795@qq.com
 */
public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer{
    private WebView mWebView = null;
    //弱引用爲了内存 webView是敏感内存
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbaiable = false;
    private LatteDelegate mTopDelegate=null;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        assert args != null;
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                //初始化WebView
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                final String name= Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);

                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebViewAbaiable = true;
            } else {
                throw new NullPointerException("Petterp/Initallize is Null!");
            }
        }
    }

    public void setTopDelegate(LatteDelegate delegate){
        mTopDelegate=delegate;
    }

    public LatteDelegate getmTopDelegate(){
        if (mTopDelegate == null) {
            mTopDelegate=this;
        }
        return mTopDelegate;
    }


    public WebView getmWebView() {
        if (mWebView == null) {
            throw new NullPointerException("Petterp/ WebView is Null");
        }
        //是否准备好了
        return mIsWebViewAbaiable ? mWebView : null;
    }

    public String getUrl(){
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbaiable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }


}
