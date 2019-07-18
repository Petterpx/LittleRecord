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
import com.petterp.latte_core.util.litepal.BillInfo;
import com.petterp.latte_core.util.litepal.EveryBillCollect;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.litepaldemo.Book;
import com.petterp.latte_ec.main.index.updateIndex.IaddData;
import com.petterp.latte_ec.main.index.add.AddDelegate;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author Petterp on 2019/6/21
 * Summary:明细界面 ->Index
 * 邮箱：1509492795@qq.com
 */
public class IndexDelegate extends BottomItemDelegate implements IaddData {
    @BindView(R2.id.index_bar)
    Toolbar toolbar = null;
    @BindView(R2.id.fb_index_top)
    FloatingActionButton floatingActionButton = null;
    @BindView(R2.id.rv_index_list)
    RecyclerView recyclerView = null;
    //数据
    private List<MultipleItemEntity> list;
    //index适配器
    private IndexAdapter adapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //初始化Rv
        initRecyclerView();
        //判断Rv是否可滑动
//        hideview();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        //降序获取数据
        List<EveryBillCollect> collects = LitePal.
                select("consume", "time")
                .order("time desc")
                .find(EveryBillCollect.class);
        int collectSize = collects.size();
        for (int i = 0; i < collectSize; i++) {
            MultipleItemEntity itemHeader = MultipleItemEntity.builder()
                    .setItemType(IndexItemType.INDEX_DETAIL_HEADER)
                    .setField(IndexFidls.TIME, collects.get(i).getTime())
                    //这里需要修改时间，目前测试
                    .setField(IndexFidls.DAY, getWeekOfDate(new Date()))
                    .setField(IndexFidls.CONSUME, collects.get(i).getConsume())
                    .build();
            list.add(itemHeader);
            List<BillInfo> billInfos = LitePal.
                    where("timeday=?", collects.get(i).getTime())
                    .find(BillInfo.class);
            int billInfoSize = billInfos.size();
            for (int j = 0; j < billInfoSize; j++) {
                MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setItemType(IndexItemType.INDEX_DETAIL_LIST)
                        .setField(IndexFidls.BILL, billInfos.get(j).getBill())
                        .setField(IndexFidls.KIND, billInfos.get(j).getKind())
                        .setField(IndexFidls.CONSUME_I, billInfos.get(j).getMoney())
                        .build();
                list.add(itemEntity);
            }
        }
        adapter = new IndexAdapter(list);
        View view = View.inflate(getContext(), R.layout.arrow_rv_foot_view, null);
        adapter.addFooterView(view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Rv分割线
        RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                .color(Color.parseColor("#F0F1F2"))
                .size(2)
                .build().addTo(recyclerView);
        floatingActionButton.setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new AddDelegate(this)));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
     * 页面可见时调用
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void addData(MultipleItemEntity entity,double money) {
        list.add(entity);
        //刷新头尾
        double consume=list.get(0).getField(IndexFidls.CONSUME);
        list.get(0).setFild(IndexFidls.CONSUME,consume+money);
        adapter.notifyItemChanged(list.size());
        adapter.notifyItemChanged(0);
    }

    public String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
