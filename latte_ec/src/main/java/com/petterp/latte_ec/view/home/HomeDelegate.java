package com.petterp.latte_ec.view.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.MessageItems;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.presenter.HomePresenter;
import com.petterp.latte_ec.view.add.AddDelegate;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页delegate
 *
 * @author by Petterp
 * @date 2019-07-23
 */
public class HomeDelegate extends LatteDelegate implements IHomeView, IHomeDrListener, IHomeRvListener {

    @BindView(R2.id.index_bar)
    Toolbar toolbar = null;
    @BindView(R2.id.fb_index_top)
    FloatingActionButton floatingActionButton = null;
    @BindView(R2.id.rv_index_list)
    RecyclerView recyclerView = null;
    @BindView(R2.id.tv_index_tob_surplus)
    AppCompatTextView tvSurplus = null;
    @BindView(R2.id.tv_index_tob_consume)
    AppCompatTextView tvConsume = null;
    @BindView(R2.id.tv_index_tob_income)
    AppCompatTextView tvIncome = null;
    @BindView(R2.id.drawer_layout)
    DrawerLayout drawerLayout = null;
    @BindView(R2.id.cord_layout)
    CoordinatorLayout right = null;
    @BindView(R2.id.tv_layout)
    TextView left = null;
    @BindView(R2.id.ic_toolbar_data_home)
    IconTextView dataTooblar;

    @SuppressLint("WrongConstant")
    @OnClick(R2.id.ic_toolbar_drawer_home)
    void startDrawer() {
        drawerLayout.openDrawer(Gravity.START);
    }

    //控制层
    private HomePresenter mPresenter;
    private HomeAdapter homeAdapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //建立连接
        mPresenter = new HomePresenter(this);
        //初始化view
        mPresenter.showInfo();
        //侧滑监听
        drawerLayout.addDrawerListener(new HomeDrawerListener(getProxyActivity(), this));
        //注册EvenBus
        EventBus.getDefault().register(this);
    }


    @Override
    public void setTitleinfo(HashMap<IHomeRvFields, String> map) {
        tvConsume.setText(map.get(IHomeRvFields.CONSUME));
        tvIncome.setText(map.get(IHomeRvFields.INCOME));
        tvSurplus.setText(map.get(IHomeRvFields.SUR_PLUS));
    }

    @Override
    public void showRv(List<MultipleItemEntity> list) {
        homeAdapter = new HomeAdapter(list);
        View view = View.inflate(getContext(), R.layout.arrow_rv_foot_view, null);
        //添加尾部
        homeAdapter.addFooterView(view);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Rv分割线
        RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                .color(Color.parseColor("#F0F1F2"))
                .size(2)
                .build().addTo(recyclerView);
        //Rv滑动监听
        recyclerView.addOnScrollListener(new HomeRvoScrollListener(this));
        //Rv点击事件
        recyclerView.addOnItemTouchListener(new HomeItemClickListener(this, mPresenter));
    }


    @Override
    public void updateRv() {
        homeAdapter.notifyDataSetChanged();
    }


    @Override
    public void setHomeOffset(int r, int b) {
        right.layout(left.getRight(), 0, left.getRight() + r, b);
    }

    @Override
    public void FloatButtonListener() {
        floatingActionButton.setOnClickListener(view -> {
            mPresenter.setKey(TimeUtils.build().getLongTimekey());
            mPresenter.setStateMode(IHomeStateType.ADD);
            //启动AddDelegate
            getSupportDelegate().start(AddDelegate.newInstance());
        });
    }


    @Override
    public View getToolbar() {
        return toolbar;
    }


    /**
     * EvenBus 接收Add传回的具体item
     * @param messageItems
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modeState(MessageItems messageItems) {
        MultipleItemEntity itemEntity = messageItems.getItemEntity();
        itemEntity.setFild(IHomeRvFields.KEY, mPresenter.getKey());
        switch (mPresenter.getStateMode()) {
            case IHomeStateType.ADD:
                mPresenter.addModel(itemEntity);
                break;
            case IHomeStateType.UPDATE:
                mPresenter.updateModel(itemEntity);
                break;
            case IHomeStateType.HEADER_ADD:
                mPresenter.addHeaderModel(itemEntity);
                break;
            default:
                break;
        }
    }


    @Override
    public void showFlootButton() {
        floatingActionButton.show();
    }

    @Override
    public void hideFlootButton() {
        floatingActionButton.hide();
    }

    /**
     * 返回键重写
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        boolean mode = super.onBackPressedSupport();
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        }
        return mode;
    }
}
