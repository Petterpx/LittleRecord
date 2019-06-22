package com.petterp.latte_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.delegates.web.WebDelegate;

/**
 * @author Petterp on 2019/4/28
 * Summary:
 * email：1509492795@qq.com
 */
public abstract   class Event  implements IEvent{
    private Context mContent=null;
    private String mAction=null;
    private WebDelegate mDelegate=null;
    private String mUrl=null;
    private WebView mWebView=null;

    public Context getContext() {
        return mContent;
    }

    public WebView getWebView(){
        return mDelegate.getmWebView();
    }

    public void setContext(Context mContent) {
        this.mContent = mContent;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
