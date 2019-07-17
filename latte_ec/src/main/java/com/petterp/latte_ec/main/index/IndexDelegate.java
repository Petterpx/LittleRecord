package com.petterp.latte_ec.main.index;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.index.add.AddDelegate;
import com.petterp.latte_ui.recyclear.ItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author Petterp on 2019/6/21
 * Summary:明细界面
 * 邮箱：1509492795@qq.com
 */
public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.index_bar)
    Toolbar toolbar = null;
    @BindView(R2.id.fb_index_top)
    FloatingActionButton floatingActionButton = null;
    @BindView(R2.id.rv_index_list)
    RecyclerView recyclerView = null;
    boolean mode = true;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                .setItemType(IndexItemType.INDEX_DETAIL_HEADER)
                .setField(IndexFidls.TIME, "7.17")
                .setField(IndexFidls.DAY, "周一")
                .setField(IndexFidls.CONSUME, "78")
                .build();
        MultipleItemEntity itemEntity1 = MultipleItemEntity.builder()
                .setItemType(IndexItemType.INDEX_DETAIL_LIST)
                .setField(IndexFidls.MODE,true)
                .setField(IndexFidls.KIND, "三餐")
                .setField(IndexFidls.CONSUME_I, "10")
                .build();
        List<MultipleItemEntity> list = new ArrayList<>();
        list.add(itemEntity);
        list.add(itemEntity1);
        list.add(itemEntity1);
        list.add(itemEntity1);

        list.add(itemEntity);
        list.add(itemEntity1);
        list.add(itemEntity1);
        list.add(itemEntity1);
        IndexAdapter adapter = new IndexAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Rv分割线
        RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                .color(Color.parseColor("#F0F1F2"))
                .size(2)
                .build().addTo(recyclerView);
        floatingActionButton.setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new AddDelegate()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    floatingActionButton.hide();
                }
                if (dy < 0) {
                    floatingActionButton.show();
                }
            }
        });
//        hideview();

    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 判断RecyclearView是否处于底部
     */
    private void hideview() {
        if (!recyclerView.canScrollVertically(1) && !recyclerView.canScrollVertically(-1)) {

        } else {

        }
    }

    /**
     *页面可见时调用
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

}
