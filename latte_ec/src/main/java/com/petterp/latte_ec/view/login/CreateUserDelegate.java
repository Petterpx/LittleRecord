package com.petterp.latte_ec.view.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import butterknife.BindView;

/**
 * 注册详细界面
 *
 * @author by Petterp
 * @date 2019-08-01
 */
public class CreateUserDelegate extends LatteDelegate {

    @BindView(R2.id.bar_user_create)
    Toolbar toolbar=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_create;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    public static CreateUserDelegate newInstance(String phone) {

        Bundle args = new Bundle();
        args.putString("phone",phone);
        CreateUserDelegate fragment = new CreateUserDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }
}
