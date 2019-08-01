package com.petterp.latte_ec.view.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.example.rxretifoit.ui.LatteLoader;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.phone.PhoneUtil;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.presenter.RegisterPresenter;
import com.petterp.latte_ec.view.login.iview.IRegisterView;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author by Petterp
 * @date 2019-07-31
 */
public class RegisterDelegate extends LatteDelegate implements IRegisterView {

    @BindView(R2.id.bar_register)
    Toolbar toolbar = null;
    @BindView(R2.id.edit_register_code)
    AppCompatEditText editCode = null;
    @BindView(R2.id.edit_register_phone)
    AppCompatEditText editPhone = null;
    @BindView(R2.id.tv_register_gain_code)
    AppCompatTextView tvTimer = null;

    private RegisterPresenter presenter;


    @OnClick(R2.id.btn_register_next)
    void onNext() {
        String code = editCode.getText().toString().trim();
        if (!PhoneUtil.isMobileNO(editPhone.getText().toString().trim())) {
            Toast.makeText(getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (code.length() != 4) {
            Toast.makeText(getContext(), "请输入正确的验证码", Toast.LENGTH_SHORT).show();
        } else {
//            presenter.setCreateCode(code);
            onclickUserInfo();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_register;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        presenter = new RegisterPresenter(this);
        tvTimer.setOnClickListener(view -> {
            String res = editPhone.getText().toString().trim();
            if (res.equals("") || !PhoneUtil.isMobileNO(res)) {
                Toast.makeText(getContext(), "请输入正确的号码", Toast.LENGTH_SHORT).show();
            } else {
                presenter.setCreateUser(res);
            }
        });

    }

    public static RegisterDelegate newInstance() {

        Bundle args = new Bundle();

        RegisterDelegate fragment = new RegisterDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void showLoader() {
        LatteLoader.showLoading(getContext());
    }

    @Override
    public void hideLoader() {
        LatteLoader.stopLoading();
    }

    @Override
    public void setTvCode(String time) {
        if (tvTimer != null) {
            tvTimer.setText(time);
        }
    }

    @Override
    public void openClick() {
        tvTimer.setEnabled(true);
    }

    @Override
    public void closeClick() {
        tvTimer.setEnabled(false);
    }

    @Override
    public void onclickUserInfo() {
        getSupportDelegate().start(CreateUserDelegate.newInstance(presenter.getPhone()));
    }

    @Override
    public void codeError() {
        Toast.makeText(getContext(), "验证码错误,请重新输入", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
