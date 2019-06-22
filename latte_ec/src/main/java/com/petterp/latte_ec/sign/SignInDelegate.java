package com.petterp.latte_ec.sign;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/4/21
 * Summary:登录
 * email：1509492795@qq.com
 */
public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_pswd)
    TextInputEditText mPswd = null;

    @BindView(R2.id.input_sign_in_email)
    TextInputLayout iEmail=null;
    @BindView(R2.id.input_sign_in_pswd)
    TextInputLayout ipswd=null;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener) {
            mISignListener = (ISignListener) context;
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWechat(){
//        LatteWeChat.getInstance().onSignSuccess(userInfo -> LatteLogger.d("demo","开始"+userInfo)).signIn();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLine(){
        getSupportDelegate().start(new SignUpDelegate(),SINGLETASK);
    }

    @OnClick(R2.id.btn_sign_in)
    void onclickSignup() {
        if (chechForm()) {
            com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
            json.put("pswd",Objects.requireNonNull(mPswd.getText()).toString());
            json.put("email",Objects.requireNonNull(mEmail.getText()).toString());
            //转为字符串
            String response=json.toJSONString();
//            LatteLogger.json("USER_PROFILE",response);
            if (!SignHandler.onSignIN(response,mISignListener)){
                Toast.makeText(getContext(), "用户信息失败", Toast.LENGTH_SHORT).show();
            }else{
//                getSupportDelegate().startWithPop(new EcBottomDelgate());
            }
        }else{
            Toast.makeText(getContext(), "验证失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {

    }

    private boolean chechForm() {
        final String email = Objects.requireNonNull(mEmail.getText()).toString();
        final String pswd = Objects.requireNonNull(mPswd.getText()).toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            iEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            iEmail.setError(null);
        }


        if (pswd.isEmpty() || pswd.length() < 6) {
            ipswd.setError("请填写至少6位密码");
            isPass = false;
        } else {
            ipswd.setError(null);
        }

        return isPass;
    }
}
