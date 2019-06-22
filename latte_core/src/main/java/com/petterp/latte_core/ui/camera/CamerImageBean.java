package com.petterp.latte_core.ui.camera;

import android.net.Uri;

/**
 * @author Petterp on 2019/6/15
 * Summary:存储一些中间值
 * email：1509492795@qq.com
 */
public final class CamerImageBean {

    private Uri mPath = null;

    private static final CamerImageBean INSTANCE = new CamerImageBean();

    public static CamerImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }

}
