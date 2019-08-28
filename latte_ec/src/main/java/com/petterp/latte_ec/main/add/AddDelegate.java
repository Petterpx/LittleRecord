package com.petterp.latte_ec.main.add;

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
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.home.IHomeTitleRvItems;
import com.petterp.latte_ec.main.add.BootomCompile.CompileItemClcikList;
import com.petterp.latte_ec.main.add.BootomCompile.CompileListAdapter;
import com.petterp.latte_ec.main.add.topViewVp.RecordFragment;
import com.petterp.latte_ec.main.add.topViewVp.RecordOnPageChangeListener;
import com.petterp.latte_ec.main.add.topViewVp.RecordPagerAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author by Petterp
 * @date 2019-07-24
 */
@CreatePresenter(AddPresenter.class)
public class AddDelegate extends BaseFragment<AddPresenter> implements IAddView {
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
    private CompileListAdapter adapter;
    private DecimalFormat decimalFormat = new DecimalFormat("###################.##");
    private RecordFragment consumeFragment;
    private RecordFragment incomeFragment;


    @Override
    public Object setLayout() {
        return R.layout.delegate_add;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mPresenter = getPresenter();
    }


    @Override
    public Bundle getBundle() {
        return getArguments();
    }

    @Override
    public void topViewPagerInfo() {
        consumeFragment = new RecordFragment(mPresenter, IAddTitleItems.CONSUME_ITEMS);
        incomeFragment = new RecordFragment(mPresenter, IAddTitleItems.INCOME_ITEMS);
        String[] sums = {IAddTitleItems.CONSUME_ITEMS, IAddTitleItems.INCOME_ITEMS};
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
        adapter = new CompileListAdapter(mPresenter.getkeyRvList());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new CompileItemClcikList(mPresenter));
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
    public void updateKeyColor(boolean mode) {
        adapter.notifyItemChanged(15);
        if (mode) {
            tvMoney.setTextColor(Color.parseColor("#F93A30"));
        } else {
            tvMoney.setTextColor(Color.parseColor("#06F985"));
        }
    }

    @Override
    public void UpdateRv() {
        IAddBundleFields fields = mPresenter.getUpdateRvItem();
        editText.setText(fields.getRemark());
        tvMoney.setText(decimalFormat.format(fields.getMoney()));
        if (fields.getCargoy().equals(IHomeTitleRvItems.CONSUME)) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }
    }

    @Override
    public void removeView() {
        consumeFragment.removeView();
        incomeFragment.removeView();
    }

    @Override
    public void addRvItem() {
        if (mPresenter.getTitleMode().equals(IHomeTitleRvItems.CONSUME)) {
            consumeFragment.addRvItem();
        } else {
            incomeFragment.addRvItem();
        }
    }

    @Override
    public void updateRvItem(int position) {
        if (mPresenter.getTitleMode().equals(IHomeTitleRvItems.CONSUME)) {
            consumeFragment.updateRvItem(position);
        } else {
            incomeFragment.updateRvItem(position);
        }
    }

    @Override
    public void delegateRvItem() {
        if (mPresenter.getTitleMode().equals(IHomeTitleRvItems.CONSUME)) {
            consumeFragment.delegateRvItem();
        } else {
            incomeFragment.delegateRvItem();
        }
    }


    @Override
    public View setToolbar() {
        return toolbar;
    }


}
