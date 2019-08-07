package com.petterp.latte_core.mvp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.IGlobalCallback;

/**
 * 抽象Activity基类，包含Fragment返回键处理
 * @author by petterp
 * @date 2019-08-07
 */
public abstract class BaseActivity extends AppCompatActivity {
    private BaseFragment fragment;
    private boolean mode;

    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        CallbackManager.getInstance().addCallback("onclick", (IGlobalCallback<BaseFragment>) args -> fragment = args);
    }

    @Override
    public void onBackPressed() {
        if (mode) {
            //执行自定义back事件
            fragment.setBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
