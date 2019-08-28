package com.petterp.latte_ec.main.intro.webview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.web.WebViewUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import butterknife.BindView;

/**
 * Intro WebViewDelegate
 * @author by petterp
 * @date 2019-08-21
 */
public class IntroWebDelegate extends BaseFragment {
    @BindView(R2.id.bar_web_report)
    Toolbar toolbar;
    @BindView(R2.id.webView_report)
    WebViewUtils webViewUtils;

    @Override
    public Object setLayout() {
        return R.layout.delegate_report_webview;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        webViewUtils.loadUrl("https://github.com/Petterpx/LittleRecord/blob/master/README.md");
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }

    @Override
    public void onResume() {
        super.onResume();
        webViewUtils.onResume();
    }

    @Override
    public void onDestroy() {
        webViewUtils.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        webViewUtils.onPause();
    }

    @Override
    public boolean setBackPress(int keycode) {
        if (webViewUtils.goBack(keycode)) {
            return true;
        }
        return false;
    }
}
