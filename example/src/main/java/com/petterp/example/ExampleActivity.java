package com.petterp.example;


import android.Manifest;

import com.petterp.latte_core.activity.BaseActivity;


/**
 * 主Activity,处理回调，全局view入口
 */
public class ExampleActivity extends BaseActivity {


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean setJurisdication() {
        addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        addPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        addPermission(Manifest.permission.CAMERA);
        return true;
    }

}
