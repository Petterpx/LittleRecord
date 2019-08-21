package com.petterp.latte_ec.view.setting;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import butterknife.BindView;

/**
 * @author by petterp
 * @date 2019-08-16
 */
public class SettingDelegate extends BaseFragment {
    @BindView(R2.id.bar_setting)
    Toolbar toolbar=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_setting;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public View setToolbar() {
        return toolbar;
    }
}
