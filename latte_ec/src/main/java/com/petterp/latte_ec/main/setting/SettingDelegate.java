package com.petterp.latte_ec.main.setting;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by petterp
 * @date 2019-08-16
 */
public class SettingDelegate extends BaseFragment {
    @BindView(R2.id.bar_setting)
    Toolbar toolbar = null;
    @BindView(R2.id.rv_settings)
    RecyclerView recyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_setting;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        ListBean fingerprint = new ListBean.Builder()
                .setmItemtype(ListItemType.ITEM_SWITCH)
                .setmText("指纹进入验证")
                .setIcon("{icon-zhiwen}")
                .build();
        List<ListBean> list = new ArrayList<>();
        list.add(fingerprint);
        final ListAdapter adapter = new ListAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }
}
