package com.petterp.latte_ec.view.analysis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.view.BaseFragment;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.presenter.DataAnalysisPresenter;
import com.petterp.latte_ec.view.analysis.dia.DateDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 数据分析 V
 *
 * @author by petterp
 * @date 2019-08-08
 */
@CreatePresenter(DataAnalysisPresenter.class)
public class DataAnalysisDelegate extends BaseFragment<DataAnalysisPresenter> implements IDataAnalysisView {
    @BindView(R2.id.tv_title_date)
    AppCompatTextView tvDate = null;
    private int year;
    private int month;

    @OnClick(R2.id.tv_title_date)
    void updateDate() {
        new DateDialogFragment(year, month).show(getFragmentManager());
    }

    @BindView(R2.id.bar_data_analysis)
    Toolbar toolbar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_data_analysis;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        String[] times = TimeUtils.build().getYearMonthDays();
        year = Integer.parseInt(times[0]);
        month = Integer.parseInt(times[1].replaceAll("^(0+)", ""));
        String[] months = getResources().getStringArray(R.array.draw_analysis_dia_date);
        StringBuilder stringBuilder=new StringBuilder();
        tvDate.setText(stringBuilder.append(year).append("年").append(months[month-1]).toString());
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }


    @Override
    public void updateData() {
        tvDate.setText(getPresenter().getTitleRes());
    }
}
