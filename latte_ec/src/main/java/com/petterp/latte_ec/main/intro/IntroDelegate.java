package com.petterp.latte_ec.main.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author by petterp
 * @date 2019-08-16
 */
public class IntroDelegate extends BaseFragment {
    @BindView(R2.id.bar_intro)
    Toolbar toolbar = null;
    @BindView(R2.id.rv_intro)
    RecyclerView recyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_intro;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        List<MultipleItemEntity> list = new ArrayList<>();
        String[] names=getResources().getStringArray(R.array.info_name);
        String[] icons=getResources().getStringArray(R.array.info_icon);
        for (int i=0;i<2;i++){
            MultipleItemEntity entityIntro = MultipleItemEntity
                    .builder()
                    .setItemType(IntroItemType.INTRO_TYPE)
                    .setField(MultipleFidls.TEXT, names[i])
                    .setField(MultipleFidls.NAME,icons[i])
                    .setField(MultipleFidls.ID, i).build();
            list.add(entityIntro);
        }
        IntroAdapter adapter = new IntroAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnItemTouchListener(new IntroItemClcikListener(this));
        //设置Rv分割线
        RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                .color(Color.parseColor("#F0F1F2"))
                .size(2)
                .build().addTo(recyclerView);
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }
}
