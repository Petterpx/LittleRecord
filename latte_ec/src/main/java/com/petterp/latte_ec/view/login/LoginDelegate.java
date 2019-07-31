package com.petterp.latte_ec.view.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.edittext.SoftKeyBoardListener;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author by Petterp
 * @date 2019-07-30
 */
public class LoginDelegate extends LatteDelegate {

    @BindView(R2.id.bar_login)
    Toolbar toolbar=null;
    @OnClick(R2.id.line_login_qq)
    void onLoginQ(){

    }
    @OnClick(R2.id.tv_login_create)
    void createUser(){
        getSupportDelegate().start(RegisterDelegate.newInstance());
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_login;
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
    public Toolbar getToolbar() {
        return toolbar;
    }
}
