package com.petterp.latte_ec.main.index;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import butterknife.BindView;

/**
 * @author Petterp on 2019/6/21
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.goods_detail_toolbar)
    Toolbar toolbar=null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }
}
