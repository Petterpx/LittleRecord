package com.petterp.latte_core.util.callback;

import android.util.Log;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author Petterp on 2019/6/15
 * Summary:全局Callback工具
 * email：1509492795@qq.com
 */
public class CallbackManager {
    public static final HashMap<Object, IGlobalCallback> CALLBACKS = new HashMap<>();

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public <T> CallbackManager addCallback(Object tag, IGlobalCallback<T> callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }

}
