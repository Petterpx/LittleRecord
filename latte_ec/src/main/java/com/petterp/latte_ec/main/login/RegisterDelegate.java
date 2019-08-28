package com.petterp.latte_ec.main.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.util.phone.PhoneUtil;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.login.iview.IRegisterView;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册-> 验证手机号
 *
 * @author by Petterp
 * @date 2019-07-31
 */
@CreatePresenter(LoginRegisterPresenter.class)
public class RegisterDelegate extends BaseFragment<LoginRegisterPresenter> implements IRegisterView {

    @BindView(R2.id.bar_register)
    Toolbar toolbar = null;
    @BindView(R2.id.edit_register_code)
    AppCompatEditText editCode = null;
    @BindView(R2.id.edit_register_phone)
    AppCompatEditText editPhone = null;
    @BindView(R2.id.tv_register_gain_code)
    AppCompatTextView tvTimer = null;

    private LoginRegisterPresenter presenter;
    private String phone = null;


    @OnClick(R2.id.btn_register_next)
    void onNext() {
        String code = editCode.getText().toString().trim();
        phone = editPhone.getText().toString().trim();
        if (!PhoneUtil.isMobileNO(phone)) {
            Toast.makeText(getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (code.length() != 4) {
            Toast.makeText(getContext(), "请输入正确的验证码", Toast.LENGTH_SHORT).show();
        } else {
            presenter.setCreateCode(code);
        }


    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_login_register;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        presenter = getPresenter();
        tvTimer.setOnClickListener(view -> {
            String res = editPhone.getText().toString().trim();
            if (res.equals("") || !PhoneUtil.isMobileNO(res)) {
                Toast.makeText(getContext(), "请输入正确的号码", Toast.LENGTH_SHORT).show();
            } else {
                presenter.setCreateUser(res);
            }
        });

    }


    @Override
    public View setToolbar() {
        return toolbar;
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
        fragmentStart(RegisterDelegateDirections.actionRegisterDelegateToCreateUserDelegate(phone));
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
