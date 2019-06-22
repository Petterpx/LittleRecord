package com.petterp.example;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.petterp.latte_core.activity.ProxyActivity;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ec.main.EcBottomDelegate;
import com.petterp.latte_ec.sign.ISignListener;
import com.petterp.latte_ui.launcher.ILauncherListener;
import com.petterp.latte_ui.launcher.OnLauncherFinishTag;


/**
 * 主Activity,处理回调，全局view入口
 */
public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener {

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }

    //隐藏actionbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    /**
     * 回调接口-> LauncherScrollDelegate ，登录了怎样怎样，没有登录怎样怎样
     *
     * @param tag
     */
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        if (tag == OnLauncherFinishTag.SIGNED) {
//            getSupportDelegate().pop();
//            getSupportDelegate().pop();
//            getSupportDelegate().startWithPop(new EcBottomDelgate());
//            getSupportDelegate().startWithPop(new EcBottomDelgate());
        } else {
            //退栈并启动
            getSupportDelegate().startWithPop(new EcBottomDelegate());
//            LatteLoader.showLoading(this);
        }
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
