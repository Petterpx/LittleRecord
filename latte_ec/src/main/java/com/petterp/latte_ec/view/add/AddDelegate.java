package com.petterp.latte_ec.view.add;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.IGlobalCallback;
import com.petterp.latte_core.util.edittext.SoftKeyBoardListener;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.model.add.IAddBundleFields;
import com.petterp.latte_ec.model.add.IAddBundleType;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeTitleRvItems;
import com.petterp.latte_ec.presenter.AddPresenter;
import com.petterp.latte_ec.view.add.BootomCompile.CompileItemClcikList;
import com.petterp.latte_ec.view.add.BootomCompile.CompileListAdapter;
import com.petterp.latte_ec.view.add.topViewVp.RecordCallbackFields;
import com.petterp.latte_ec.view.add.topViewVp.RecordFragment;
import com.petterp.latte_ec.view.add.topViewVp.RecordOnPageChangeListener;
import com.petterp.latte_ec.view.add.topViewVp.RecordPagerAdapter;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * @author by Petterp
 * @date 2019-07-24
 */
public class AddDelegate extends LatteDelegate implements IAddView, IGlobalCallback<String[]> {

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


    @Override
    public Object setLayout() {
        return R.layout.delegate_add;
    }

    public static AddDelegate newInstance(IAddBundleFields addBundleFields) {
        Bundle args = new Bundle();
        args.putParcelable(IAddBundleType.KEY_UPDATE_LIST, addBundleFields);
        AddDelegate fragment = new AddDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    public static AddDelegate newInstance() {
        Bundle args = new Bundle();
        AddDelegate fragment = new AddDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //建立连接
        mPresenter = new AddPresenter(this);
        //初始化View
        mPresenter.showInfo(getArguments());
        //处理键盘冲突
        initEditKey();
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
    public void topViewPagerInfo() {
        RecordFragment consumeFragment = new RecordFragment(mPresenter.getConsumeRvList(), mPresenter.getTitleRvKind()[1]);
        RecordFragment incomeFragment = new RecordFragment(mPresenter.getIncomeRvList(), mPresenter.getTitleRvKind()[1]);
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
        //注册CallBack
        CallbackManager.getInstance().addCallback(RecordCallbackFields.ADD_RV_KIND, this);
    }

    @Override
    public void bottomKeyInfo() {
        adapter = new CompileListAdapter(mPresenter.getkeyRvList());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new CompileItemClcikList(mPresenter, this));
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
        String money = "" + fields.getMoney();
        tvMoney.setText(money);
        if (fields.getCargoy().equals(IHomeTitleRvItems.CONSUME)) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }
    }


    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除EvenBus绑定
        EventBus.getDefault().unregister(this);
    }


    /**
     * TopRv 点击的kind
     *
     * @param
     */
    @Override
    public void executeCallback(@Nullable String[] args) {
        mPresenter.setTitleRvKind(args);
    }

}
