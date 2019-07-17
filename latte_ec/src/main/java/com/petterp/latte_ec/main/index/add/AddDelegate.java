package com.petterp.latte_ec.main.index.add;

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
import com.petterp.latte_core.util.edittext.SoftKeyBoardListener;
import com.petterp.latte_core.util.litepal.OutputInfo;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.index.add.BootomCompile.CompileItemClcikList;
import com.petterp.latte_ec.main.index.add.BootomCompile.CompileListAdapter;
import com.petterp.latte_ec.main.index.add.BootomCompile.CompileListItemType;
import com.petterp.latte_ec.main.index.add.BootomCompile.IaddInform;
import com.petterp.latte_ec.main.index.add.TopViewPager.ConsumeFragment;
import com.petterp.latte_ec.main.index.add.TopViewPager.ConsumePagerAdapter;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

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
public class AddDelegate extends LatteDelegate implements IaddInform {
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


    @OnClick(R2.id.it_index_add_back)
    void addBack() {
        getSupportDelegate().pop();
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
        list.add(new ConsumeFragment());
        list.add(new ConsumeFragment());
        String[] sums = {"支出", "收入"};
        ConsumePagerAdapter adapter = new ConsumePagerAdapter(getChildFragmentManager(), list, sums);
        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(this);
        tabLayout.addTab(tabLayout.newTab().setText("支出"));
        tabLayout.addTab(tabLayout.newTab().setText("收入"));
        tabLayout.setupWithViewPager(viewPager);
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
        String remark = Objects.requireNonNull(editText.getText()).toString().trim();
        new OutputInfo(System.currentTimeMillis(), money, remark, "petterp").save();
        getSupportDelegate().pop();
    }
}
