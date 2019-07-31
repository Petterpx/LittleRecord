package com.petterp.example;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.petterp.latte_core.activity.ProxyActivity;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ec.view.home.HomeDelegate;

import me.yokeyword.fragmentation.anim.FragmentAnimator;


/**
 * 主Activity,处理回调，全局view入口
 */
public class ExampleActivity extends ProxyActivity{

    @Override
    public LatteDelegate setRootDelegate() {
        return new HomeDelegate();
    }

    //隐藏actionbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("demo","app开始");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        //绑定EvenBus
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
