package com.petterp.latte_ec.main.report;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author by petterp
 * @date 2019-08-16
 */
public class ReportDelegate extends BaseFragment {
    @BindView(R2.id.bar_report)
    Toolbar toolbar = null;
    @BindView(R2.id.text_report_error)
    AppCompatTextView tvError = null;
    @BindView(R2.id.text_report_suggest)
    AppCompatTextView tvsuggest = null;
    @BindView(R2.id.text_report_rest)
    AppCompatTextView tvRest = null;
    private Disposable subscribe;
    @BindView(R2.id.edit_report)
    AppCompatEditText editText=null;

    @OnClick({ R2.id.text_report_error, R2.id.text_report_suggest, R2.id.text_report_rest})
    void onClick(View view) {
        tvError.setTextColor(Color.parseColor("#93A8B1"));
        tvError.setBackgroundResource(R.drawable.report_text_up);
        tvsuggest.setTextColor(Color.parseColor("#93A8B1"));
        tvsuggest.setBackgroundResource(R.drawable.report_text_up);
        tvRest.setTextColor(Color.parseColor("#93A8B1"));
        tvRest.setBackgroundResource(R.drawable.report_text_up);
        int id = view.getId();
        if (id == R.id.text_report_error) {
            tvError.setTextColor(Color.parseColor("#2A9BF2"));
            tvError.setBackgroundResource(R.drawable.report_text_to);
        } else if (id == R.id.text_report_suggest) {
            tvsuggest.setTextColor(Color.parseColor("#2A9BF2"));
            tvsuggest.setBackgroundResource(R.drawable.report_text_to);
        } else {
            tvRest.setTextColor(Color.parseColor("#2A9BF2"));
            tvRest.setBackgroundResource(R.drawable.report_text_to);
        }
    }

    @OnClick(R2.id.text_report_submit)
    void onSubmit() {
        if (!editText.getText().toString().trim().isEmpty()){
            showLoader();
            subscribe = Observable.interval(1000, TimeUnit.MILLISECONDS)
                    .take(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> {
                        stopLoader();
                        Toast.makeText(getContext(), "提交成功!", Toast.LENGTH_SHORT).show();
                    }).subscribe();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_report;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public View setToolbar() {
        return toolbar;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (subscribe != null && !subscribe.isDisposed()) {
            stopLoader();
            subscribe.dispose();
            subscribe = null;
        }
    }
}
