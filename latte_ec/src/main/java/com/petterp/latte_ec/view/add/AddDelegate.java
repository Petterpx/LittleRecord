package com.petterp.latte_ec.view.add;

import android.graphics.Color;
import android.os.Bundle;
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
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.index.add.BootomCompile.CompileListAdapter;
import com.petterp.latte_ec.main.index.add.TopViewPager.RecordPagerAdapter;
import com.petterp.latte_ec.presenter.AddPresenter;
import com.petterp.latte_ec.view.add.BootomCompile.CompileItemClcikList;
import com.petterp.latte_ec.view.add.topViewVp.RecordFragment;
import com.petterp.latte_ec.view.add.topViewVp.RecordOnPageChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author by Petterp
 * @date 2019-07-24
 */
public class AddDelegate extends LatteDelegate implements IAddView {

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
    AppCompatTextView tvMoney = null;
    private AddPresenter mPresenter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_add;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //建立连接
        mPresenter = new AddPresenter(this);
        //初始化View
        mPresenter.showInfo();
    }

    @Override
    public void topViewPagerInfo() {
        RecordFragment consumeFragment = new RecordFragment(mPresenter.getConsumeRvList());
        RecordFragment incomeFragment = new RecordFragment(mPresenter.getIncomeRvList());
        String[] sums = {"支出", "收入"};
        List<Fragment> list = new ArrayList<>();
        list.add(consumeFragment);
        list.add(incomeFragment);
        RecordPagerAdapter adapter = new RecordPagerAdapter(getChildFragmentManager(), list, sums);
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText(sums[0]));
        tabLayout.addTab(tabLayout.newTab().setText(sums[1]));
        //关联Tablayout
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new RecordOnPageChangeListener(mPresenter));
    }

    @Override
    public void bottomKeyInfo() {
        CompileListAdapter adapter = new CompileListAdapter(mPresenter.getkeyRvList());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new CompileItemClcikList(mPresenter,getParentDelegate()));
        //设置Rv分割线
        RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                .color(Color.parseColor("#F0F1F2"))
                .size(2)
                .build().addTo(recyclerView);
    }

    @Override
    public void setKeyRes(String res) {
        tvMoney.setText(res);
    }

    @Override
    public String getEditRemark() {
        return Objects.requireNonNull(editText.getText()).toString();
    }


    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

}
