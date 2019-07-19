package com.petterp.example;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.petterp.latte_core.activity.ProxyActivity;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ec.main.EcBottomDelegate;
import com.petterp.latte_ec.sign.ISignListener;


/**
 * 主Activity,处理回调，全局view入口
 */
public class ExampleActivity extends ProxyActivity implements ISignListener {

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
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
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
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
