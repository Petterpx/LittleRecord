package com.petterp.latte_ec.sign;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.google.android.material.textfield.TextInputEditText;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.log.LatteLogger;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/4/21
 * Summary:注册逻辑
 * email：1509492795@qq.com
 */
public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_pswd)
    TextInputEditText mPswd = null;
    @BindView(R2.id.edit_sign_up_re_pswd)
    TextInputEditText mRePswd = null;


    private ISignListener mISignListener=null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener) {
            mISignListener = (ISignListener) context;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onclickSignup() {
        if (chechForm()) {
//            TestGet();
            JSONObject json=new JSONObject();
            json.put("name",Objects.requireNonNull(mName.getText()).toString());
            json.put("email",Objects.requireNonNull(mEmail.getText()).toString());
            json.put("phone",Objects.requireNonNull(mPhone.getText()).toString());
            json.put("pswd",Objects.requireNonNull(mPswd.getText()).toString());
            //转为字符串
            String response=json.toJSONString();
            LatteLogger.json("USER_PROFILE",response);
            SignHandler.onSignUP(response,mISignListener);
        }else{
            Toast.makeText(getContext(), "验证失败", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R2.id.tv_link_sign_in)
    void onClickLine(){
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean chechForm() {
        final String name = Objects.requireNonNull(mName.getText()).toString();
        final String email = Objects.requireNonNull(mEmail.getText()).toString();
        final String phone = Objects.requireNonNull(mPhone.getText()).toString();
        final String pswd = Objects.requireNonNull(mPswd.getText()).toString();
        final String repswd = Objects.requireNonNull(mRePswd.getText()).toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;

        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;


        } else {
            mPhone.setError(null);
        }

        if (pswd.isEmpty() || pswd.length() < 6) {
            mPswd.setError("请填写至少6位密码");
            isPass = false;

        } else {
            mPswd.setError(null);
        }

        if (repswd.isEmpty() || repswd.length() < 6 || !(repswd.equals(pswd))) {
            mRePswd.setError("密码认证错误");
            isPass = false;

        } else {
            mRePswd.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {

    }

}
