package com.petterp.latte_ec.main.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.login.iview.ILoginView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * 登录
 *
 * @author by Petterp
 * @date 2019-07-30
 */
@CreatePresenter(LoginPresenter.class)
public class LoginDelegate extends BaseFragment<LoginPresenter> implements ILoginView,PlatformActionListener {

    @BindView(R2.id.bar_login)
    Toolbar toolbar = null;

    @OnClick(R2.id.line_login_qq)
    void onLoginQ() {
        showLoader();
        Platform plat = ShareSDK.getPlatform(QQ.NAME);
        plat.removeAccount(false); //移除授权状态和本地缓存，下次授权会重新授权
        plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
        plat.setPlatformActionListener(this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
        ShareSDK.setActivity(Latte.getBaseActivity());//抖音登录适配安卓9.0
        plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
    }

    @OnClick(R2.id.tv_login_create)
    void createUser() {
        fragmentStart(R.id.action_loginDelegate_to_registerDelegate);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_login_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public View setToolbar() {
        return toolbar;
    }


    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        getPresenter().saveQQinfo(platform);
        stopLoader();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        onLoginQ();
    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

}
