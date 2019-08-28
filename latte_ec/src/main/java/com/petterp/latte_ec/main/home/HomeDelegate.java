package com.petterp.latte_ec.main.home;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.home.draw.DrawAdapter;
import com.petterp.latte_ec.main.home.draw.DrawFields;
import com.petterp.latte_ec.main.home.draw.DrawItemClickListener;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 首页delegate
 *
 * @author by Petterp
 * @date 2019-07-23
 */
@GlideModule
@CreatePresenter(HomePresenter.class)
public class HomeDelegate extends BaseFragment<HomePresenter> implements IHomeView, IHomeDrListener, IHomeRvListener {

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
    @BindView(R2.id.line_home_layout)
    LinearLayoutCompat layoutCompat = null;
    @BindView(R2.id.rv_home_draw)
    RecyclerView drawRv = null;
    @BindView(R2.id.tv_draw_login)
    AppCompatTextView tvLogin = null;

    @BindView(R2.id.img_draw_user_avatar)
    CircleImageView circleImageView = null;
    @BindView(R2.id.tv_draw_user_record)
    AppCompatTextView tvRecord = null;
    @BindView(R2.id.li_draw_home_back)
    LinearLayoutCompat liDrawback = null;
    @BindView(R2.id.ln_home_back)
    LinearLayoutCompat linearLayoutCompat=null;

    @OnClick(R2.id.img_draw_user_avatar)
    void onStartUser() {
        fragmentStart(R.id.action_homeDelegate_to_userDelegate);
    }

    @OnClick(R2.id.tv_draw_login)
    void onLogin() {
        fragmentStart(R.id.action_homeDelegate_to_loginDelegate);
    }


    @SuppressLint("WrongConstant")
    @OnClick(R2.id.ic_toolbar_drawer_home)
    void startDrawer() {
        drawerLayout.openDrawer(Gravity.START);
    }

    @OnClick(R2.id.ic_toolbar_data_home)
    void startAnalysis()
    {
        fragmentStart(R.id.action_homeDelegate_to_dataAnalysisDelegate);
    }

    //控制层
    private HomePresenter mPresenter;
    private HomeAdapter homeAdapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }


    @SuppressLint("CheckResult")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        getActivity().getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //建立连接
        mPresenter = getPresenter();
        //侧滑监听
        drawerLayout.addDrawerListener(new HomeDrawerListener(getActivity(), this));
        Glide.with(this).asBitmap().load(R.mipmap.testbj).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Drawable drawable = new BitmapDrawable(resource);
                linearLayoutCompat.setBackground(drawable);
//                linearLayoutCompat.getBackground().setAlpha(200);
            }
        });
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
        recyclerView.addOnItemTouchListener(new HomeItemClickListener(getChildFragmentManager(), mPresenter));
    }


    @Override
    public void updateRv() {
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDrawInfo() {
        Resources resource = getResources();
        String[] itemIc = resource.getStringArray(R.array.draw_home_ic_values);
        String[] itemTv = resource.getStringArray(R.array.draw_home_tv_values);
        int size = itemIc.length;
        ArrayList<MultipleItemEntity> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(DrawFields.DRAW_HOME_FILEDS)
                    .setField(MultipleFidls.NAME, itemIc[i])
                    .setField(MultipleFidls.ID, i)
                    .setField(MultipleFidls.TEXT, itemTv[i]).build();
            list.add(itemEntity);
        }
        DrawAdapter adapter = new DrawAdapter(list);
        drawRv.setAdapter(adapter);
        drawRv.setLayoutManager(new LinearLayoutManager(getContext()));
        drawRv.addOnItemTouchListener(new DrawItemClickListener(this));
        Glide.with(this).asBitmap().load(R.mipmap.drawbj).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Drawable drawable = new BitmapDrawable(resource);
                drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                liDrawback.setBackground(drawable);
                liDrawback.getBackground().setAlpha(200);
            }
        });
        updateDrawUser();
        updateDrawKeySum();
    }

    @Override
    public void updateDrawUser() {
        if (!LatterPreference.getLoginMode()) {
            Glide.with(this).load(R.mipmap.icon).into(circleImageView);
            circleImageView.setEnabled(false);
            tvLogin.setVisibility(View.VISIBLE);
        } else {
            circleImageView.setEnabled(true);
            Glide.with(this).load(mPresenter.getDrawUserUrl()).into(circleImageView);
            tvLogin.setVisibility(View.GONE);
        }
    }


    public void updateDrawKeySum() {
        tvRecord.setText(mPresenter.getDrawRecord());
    }

    @Override
    public void updateItem() {
        homeAdapter.notifyDataSetChanged();
    }


    @Override
    public void setHomeOffset(int r, int b) {
        right.layout(layoutCompat.getRight(), 0, layoutCompat.getRight() + r, b);
    }

    @Override
    public void FloatButtonListener() {
        floatingActionButton.setOnClickListener(view -> {
            mPresenter.setKey(TimeUtils.build().getLongTimekey());
            mPresenter.setStateMode(IHomeStateType.ADD);
            //启动AddDelegate
            fragmentStart(R.id.action_homeDelegate_to_addDelegate);
        });
    }


    @Override
    public View setToolbar() {
        return toolbar;
    }


    @Override
    public void showFlootButton() {
        floatingActionButton.show();
    }

    @Override
    public void hideFlootButton() {
        floatingActionButton.hide();
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean setBackPress(int keycode) {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
            return true;
        }
        return false;
    }


}
