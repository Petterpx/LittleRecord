package com.petterp.latte_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.petterp.latte_core.delegates.web.event.Event;
import com.petterp.latte_core.delegates.web.event.EventManager;

/**
 * @author Petterp on 2019/4/27
 * Summary:与原生进行交互
 * email：1509492795@qq.com
 */
public final class LatteWebInterface {
    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event=EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
