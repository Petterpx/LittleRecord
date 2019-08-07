package com.petterp.latte_core.mvp.view;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.petterp.latte_core.app.Latte;

/**
 * 抽象Activity基类，包含Fragment返回键处理
 *
 * @author by petterp
 * @date 2019-08-07
 */
public abstract class BaseActivity extends AppCompatActivity {
    private BackPressFragment iBack;

    public interface BackPressFragment {
        /**
         * 相应逻辑方法
         * @return
         */
        boolean setBackPress();
    }

    public void setiBack(BackPressFragment back) {
        this.iBack = back;
    }


    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //添加Activity
        Latte.getConfigurator().withBaseActivity(this);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //执行相应方法，成功拦截，否则默认执行
        if (iBack.setBackPress()) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
