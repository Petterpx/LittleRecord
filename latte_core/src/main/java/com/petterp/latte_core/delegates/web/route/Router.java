package com.petterp.latte_core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.delegates.web.WebDelegate;
import com.petterp.latte_core.delegates.web.WebDelegateImpl;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * email：1509492795@qq.com
 */
public class Router {
    private Router() {

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public final boolean handleWebUrl(WebDelegate delegate, String url) {

        //如果是一个电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getmTopDelegate();

        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getmWebView(), url);
    }

    private void callPhone(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
        context.startActivity(intent);
        ContextCompat.startActivity(context, intent, null);
    }
}
