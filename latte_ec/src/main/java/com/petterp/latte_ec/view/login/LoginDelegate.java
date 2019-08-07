package com.petterp.latte_ec.view.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.Navigation;

import com.petterp.latte_core.mvp.view.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * @author by Petterp
 * @date 2019-07-30
 */
public class LoginDelegate extends BaseFragment<LoginPresenter> {

    @BindView(R2.id.bar_login)
    Toolbar toolbar=null;
    @OnClick(R2.id.line_login_qq)
    void onLoginQ(){

    }
    @OnClick(R2.id.tv_login_create)
    void createUser(){
        Navigation.findNavController(getRootView()).navigate(R.id.action_loginDelegate_to_registerDelegate);
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_login_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
//        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
//            @Override
//            public void keyBoardShow(int height) {
//                layoutCompat.setBackground(null);
//            }
//
//            @Override
//            public void keyBoardHide(int height) {
//                layoutCompat.setBackgroundColor(Color.WHITE);
//            }
//        });
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }


}
