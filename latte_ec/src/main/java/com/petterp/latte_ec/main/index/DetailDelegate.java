package com.petterp.latte_ec.main.index;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ui.recyclear.ItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Petterp on 2019/6/21
 * Summary:明细界面
 * 邮箱：1509492795@qq.com
 */
public class DetailDelegate extends BottomItemDelegate {
    @BindView(R2.id.index_bar)
    Toolbar toolbar = null;
    @BindView(R2.id.fb_index_top)
    FloatingActionButton floatingActionButton = null;
    @BindView(R2.id.rv_index_list)
    RecyclerView recyclerView = null;
    boolean mode=true;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                .setItemType(ItemType.TEXT)
                .setField(MultipleFidls.TEXT, "123456").build();
        List<MultipleItemEntity> list = new ArrayList<>();
        list.add(itemEntity);
        list.add(itemEntity);
        list.add(itemEntity);
        list.add(itemEntity);

        MultipleRecyclearAdapter adapter = MultipleRecyclearAdapter.create(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(itemEntity);
                adapter.notifyDataSetChanged();
                hideview();
            }
        });
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
        hideview();
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     *判断RecyclearView是否处于底部
     */
    private void hideview() {
        if (!recyclerView.canScrollVertically(1)&&!recyclerView.canScrollVertically(-1)) {
            Toast.makeText(_mActivity, "不可滑动", Toast.LENGTH_SHORT).show();
        }else{

        }
    }
}
