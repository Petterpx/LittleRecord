package com.petterp.latte_core.delegates.bottom;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.petterp.latte_core.R;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;

import java.util.Objects;

/**
 * @author Petterp on 2019/4/22
 * Summary:每一个页面(或者说每一个子项的具体超类)->抽象基类
 * email：1509492795@qq.com
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @SuppressLint("NewApi")
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            Objects.requireNonNull(getActivity()).finish();
            System.exit(0);
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
