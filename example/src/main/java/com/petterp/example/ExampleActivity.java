package com.petterp.example;


import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.petterp.latte_core.mvp.base.BaseActivity;
import com.petterp.latte_core.util.fintpaintfmanager.FingerprintUtils;
import com.petterp.latte_core.util.fintpaintfmanager.IfinderPaintf;
import com.petterp.latte_core.util.storage.LatterPreference;

import java.lang.ref.SoftReference;


/**
 * 主Activity,处理回调，全局view入口
 */
public class ExampleActivity extends BaseActivity implements IfinderPaintf {

    private SoftReference<View> fragment;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoftReference<FingerprintUtils> reference = new SoftReference<>(new FingerprintUtils(this, this));
        if (LatterPreference.getFinderPaintf()) {
            fragment = new SoftReference<>(findViewById(R.id.fragment));
            fragment.get().setVisibility(View.GONE);
            reference.get().init();
        }
    }

    @Override
    public void success() {
        fragment.get().setVisibility(View.VISIBLE);
    }

    @Override
    public void failed() {

    }

    @Override
    public void error() {
        finish();
        System.exit(0);
    }
}
