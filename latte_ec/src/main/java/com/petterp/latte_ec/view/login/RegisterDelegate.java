package com.petterp.latte_ec.view.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

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
    @BindView(R2.id.tv_register_gain_code)
    AppCompatTextView tvCode = null;
    @BindView(R2.id.edit_register_phone)
    AppCompatTextView tvPhone = null;
    private RegisterPresenter presenter;

    @OnClick(R2.id.tv_register_gain_code)
    void onGetCode() {
        String res = tvCode.getText().toString().trim();
        if (res.equals("") || !PhoneUtil.isMobileNO(res)) {
            Toast.makeText(getContext(), "请输入正确的号码", Toast.LENGTH_SHORT).show();
        } else {
            presenter.setCreateUser(res);
        }
    }

    @OnClick(R2.id.btn_register_next)
    void onNext() {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_register;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        presenter = new RegisterPresenter(this);
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

    }

    @Override
    public void hideLoader() {

    }
}
