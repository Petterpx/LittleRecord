package com.petterp.latte_ec.view.analysis;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.mvp.view.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.presenter.DataAnalysisPresenter;

import butterknife.BindView;

/**
 * 数据分析 V
 * @author by petterp
 * @date 2019-08-08
 */
public class DataAnalysisDelegate extends BaseFragment<DataAnalysisPresenter> {

    @BindView(R2.id.bar_data_analysis)
    Toolbar toolbar=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_data_analysis;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public View setToolbar() {
        return toolbar;
    }
}
