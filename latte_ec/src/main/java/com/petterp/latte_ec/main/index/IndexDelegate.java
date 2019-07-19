package com.petterp.latte_ec.main.index;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_core.util.litepal.BillInfo;
import com.petterp.latte_core.util.litepal.EveryBillCollect;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.litepaldemo.Book;
import com.petterp.latte_ec.main.index.updateIndex.IaddData;
import com.petterp.latte_ec.main.index.add.AddDelegate;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.litepal.LitePal;

import java.text.DecimalFormat;
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
public class IndexDelegate extends BottomItemDelegate implements IaddData{
    @BindView(R2.id.index_bar)
    Toolbar toolbar = null;
    @BindView(R2.id.fb_index_top)
    FloatingActionButton floatingActionButton = null;
    @BindView(R2.id.rv_index_list)
    RecyclerView recyclerView = null;
    @BindView(R2.id.tv_index_tob_surplus)
    AppCompatTextView tvSurplus=null;
    @BindView(R2.id.tv_index_tob_consume)
    AppCompatTextView tvConsume=null;
    @BindView(R2.id.tv_index_tob_income)
    AppCompatTextView tvIncome=null;
    //数据
    private List<MultipleItemEntity> list;
    //index适配器
    private IndexAdapter adapter;
    //本月收入
    private double consumeMoney=0.0;
    //本月消费
    private double incomeMoney=0.0;
    //当前位置
    private int postion=1;
    private DecimalFormat decimalFormat=new DecimalFormat("0.00");
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initRecyclerView();
        //判断Rv是否可滑动
//        hideview();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();

        //初始化Rv数据
        initRvData();

        adapter = new IndexAdapter(list);
        View view = View.inflate(getContext(), R.layout.arrow_rv_foot_view, null);
        //添加尾部
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

    private void initRvData() {
        list.clear();
        //降序获取数据
        List<EveryBillCollect> collects = LitePal.
                select("consume", "time", "day","income")
                .order("time desc")
                .find(EveryBillCollect.class);
        int collectSize = collects.size();
        for (int i = 0; i < collectSize; i++) {
            //如果是当天改为今天
            String day = collects.get(i).getDay();
            if (day.equals(TimeUtils.getday())) {
                day = "今天";
            }
            MultipleItemEntity itemHeader = MultipleItemEntity.builder()
                    .setItemType(IndexItemType.INDEX_DETAIL_HEADER)
                    .setField(IndexFidls.TIME, collects.get(i).getTime())
                    .setField(IndexFidls.DAY, day)
                    .setField(IndexFidls.CONSUME, collects.get(i).getConsume())
                    .build();
            //添加头
            list.add(itemHeader);
            //获取相应时间的具体item
            List<BillInfo> billInfos = LitePal.
                    where("timeday=?", collects.get(i).getTime())
                    .find(BillInfo.class);
            int billInfoSize = billInfos.size();
            if (i==0){
                postion=billInfoSize+1;
            }
            for (int j = 0; j < billInfoSize; j++) {
                MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setItemType(IndexItemType.INDEX_DETAIL_LIST)
                        .setField(IndexFidls.BILL, billInfos.get(j).getBill())
                        .setField(IndexFidls.KIND, billInfos.get(j).getKind())
                        .setField(IndexFidls.CONSUME_I, billInfos.get(j).getMoney())
                        .build();
                //添加具体item
                list.add(itemEntity);
            }
            consumeMoney+=collects.get(i).getConsume();
            incomeMoney+=collects.get(i).getIncome();
        }
        //初始化tooblar信息
        initTop();
    }

    @SuppressLint("SetTextI18n")
    private void initTop(){
        tvConsume.setText("月支出："+ consumeMoney);
        tvIncome.setText("月收入：" + incomeMoney);
        tvSurplus.setText(decimalFormat.format(incomeMoney + consumeMoney));
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


    @Override
    public void addData(MultipleItemEntity entity, double money) {
        if (list.size()==0){
            initRvData();
        }else{
            list.add(postion,entity);
            postion+=1;
            double consume = list.get(0).getField(IndexFidls.CONSUME);
            list.get(0).setFild(IndexFidls.CONSUME, Double.parseDouble(decimalFormat.format(consume + Math.abs(money))));
        }
        if (entity.getField(IndexFidls.BILL).equals("收入")){
            incomeMoney+=money;
        }else{
            consumeMoney+=money;
        }
        initTop();
        adapter.notifyDataSetChanged();
    }


}
