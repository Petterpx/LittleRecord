package com.petterp.latte_ec.main.index.add;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.android.material.tabs.TabLayout;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.edittext.SoftKeyBoardListener;
import com.petterp.latte_core.util.litepal.BillInfo;
import com.petterp.latte_core.util.litepal.EveryBillCollect;
import com.petterp.latte_core.util.time.SystemClock;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.litepaldemo.Book;
import com.petterp.latte_ec.main.index.IndexFidls;
import com.petterp.latte_ec.main.index.IndexItemType;
import com.petterp.latte_ec.main.index.add.BootomCompile.CompileItemClcikList;
import com.petterp.latte_ec.main.index.add.BootomCompile.CompileListAdapter;
import com.petterp.latte_ec.main.index.add.BootomCompile.CompileListItemType;
import com.petterp.latte_ec.main.index.add.BootomCompile.IaddInform;
import com.petterp.latte_ec.main.index.add.TopViewPager.RecordFragment;
import com.petterp.latte_ec.main.index.add.TopViewPager.RecordListItemType;
import com.petterp.latte_ec.main.index.add.TopViewPager.RecordPagerAdapter;
import com.petterp.latte_ec.main.index.updateIndex.IaddData;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加
 *
 * @author Petterp
 */
public class AddDelegate extends LatteDelegate implements IaddInform, Ikind {
    @BindView(R2.id.index_bar_add)
    Toolbar toolbar = null;
    @BindView(R2.id.vp_index_add)
    ViewPager viewPager = null;
    @BindView(R2.id.tl_add_vp)
    TabLayout tabLayout = null;
    @BindView(R2.id.edit_add_remark)
    AppCompatEditText editText = null;
    @BindView(R2.id.in_compile_item)
    LinearLayoutCompat layoutCompat = null;
    @BindView(R2.id.rv_add_compile)
    RecyclerView recyclerView = null;
    @BindView(R2.id.tv_add_compile_money)
    AppCompatTextView tvmoney = null;

    //默认为消费状态
    private int mode = RecordItems.CONSUME_ITEMS;
    //默认为消费三餐
    private String mkind = "三餐";

    @SuppressLint("SimpleDateFormat")

    @OnClick(R2.id.it_index_add_back)
    void addBack() {
        getSupportDelegate().pop();
    }

    private IaddData iaddData;

    public AddDelegate(IaddData iaddData) {
        this.iaddData = iaddData;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_add;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //初始化tablayout+Viewapger
        initViewPager();
        //处理键盘冲突
        initEditKey();
        //初始化自定义键盘
        initKeyBorad();
        Log.e("Demo", "add执行");
    }

    private void initKeyBorad() {
        String[] values = getResources().getStringArray(R.array.index_add_compile_values);
        List<MultipleItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(CompileListItemType.ITEM_COMPILE)
                    .setField(MultipleFidls.NAME, values[i])
                    .setField(MultipleFidls.ID, i)
                    .build();
            list.add(itemEntity);
        }
        CompileListAdapter adapter = new CompileListAdapter(list);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new CompileItemClcikList(this, this));
        //设置Rv分割线
        RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                .color(Color.parseColor("#F0F1F2"))
                .size(2)
                .build().addTo(recyclerView);
    }

    private void initViewPager() {
        List<Fragment> list = new ArrayList<>();
        List<MultipleItemEntity> itemEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, "{icon-award}")
                    .setField(IndexFidls.KIND, "三餐")
                    .setField(MultipleFidls.ID, "" + i)
                    .build();
            itemEntities.add(itemEntity);
        }
        list.add(new RecordFragment(this, itemEntities));
        list.add(new RecordFragment(this, itemEntities));
        String[] sums = {"支出", "收入"};
        RecordPagerAdapter adapter = new RecordPagerAdapter(getChildFragmentManager(), list, sums);
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("支出"));
        tabLayout.addTab(tabLayout.newTab().setText("收入"));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mode = RecordItems.CONSUME_ITEMS;
                } else {
                    mode = RecordItems.INCOME_ITEMS;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEditKey() {
        //处理键盘弹出冲突
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                layoutCompat.setVisibility(View.GONE);
            }

            @Override
            public void keyBoardHide(int height) {
                layoutCompat.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setMoney(String res) {
        tvmoney.setText(res);
    }

    @Override
    public void setSave(Double money) {
        //获取备注
        String remark = Objects.requireNonNull(editText.getText()).toString().trim();
        //当前位置是支出还是收入
        String bill;
        //当前时间 年-月-日
        String timeday = TimeUtils.build().getDate();
        //判断是否已经存入当天数据
        List<EveryBillCollect> collects
                = LitePal.where("dateinfo=?", timeday)
                .select("consume", "income", "sum")
                .find(EveryBillCollect.class);
        EveryBillCollect collect = new EveryBillCollect();
        if (mode == RecordItems.CONSUME_ITEMS) {
            money = -1 * money;
            bill = "支出";
            if (collects.size() > 0) {
                collect.setConsume(collects.get(0).getConsume() + money);
                collect.setSum(collects.get(0).getSum() + 1);
            } else {
                new EveryBillCollect(SystemClock.now(), "petterp", money, 0.0, 1).save();
            }
        } else {
            if (collects.size() > 0) {
                collect.setIncome(collects.get(0).getIncome() + money);
                collect.setSum(collects.get(0).getSum() + 1);
            } else {
                new EveryBillCollect(SystemClock.now(), "petterp", 0.0, money, 1).save();
            }
            bill = "收入";
        }
        //修改数据
        collect.updateAll("dateinfo=?", timeday);

        BillInfo info = new BillInfo(SystemClock.now(), money, remark, "petterp", mkind, bill);
        info.save();
        getSupportDelegate().pop();

        MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                .setItemType(IndexItemType.INDEX_DETAIL_LIST)
                .setField(IndexFidls.BILL, bill)
                .setField(IndexFidls.KIND, mkind)
                .setField(IndexFidls.CONSUME_I, money)
                .build();
        //接口->主页adapter添加数据
        Log.e("demo","asdasdads"+money);
        iaddData.addData(itemEntity,money);
    }


    /**
     * 获取选择的种类
     *
     * @param res
     */
    @Override
    public void kindData(String res) {
        mkind = res;
    }
}
