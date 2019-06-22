package com.petterp.latte_core.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @author Petterp on 2019/4/28
 * Summary:
 * email：1509492795@qq.com
 */
public class WebChromeClientImpl extends WebChromeClient {
    //用于拦截，比如弹出自己的语法框
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
