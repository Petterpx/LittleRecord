package com.petterp.latte_core.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author by Petterp
 * @date 2019-08-02
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract  int setLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //优化内存
        System.gc();
        System.runFinalization();

    }
}
