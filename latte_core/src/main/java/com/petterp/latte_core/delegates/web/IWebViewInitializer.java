package com.petterp.latte_core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * email：1509492795@qq.com
 */
public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    /**
     * 针对于外部，浏览器本身的控制
     *
     * @return
     */
    WebViewClient initWebViewClient();

    /**
     * 针对于内部，页面的控制
     *
     * @return
     */
    WebChromeClient initWebChromeClient();

}
