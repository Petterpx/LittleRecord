package com.petterp.latte_ec.view.add.topViewVp.rvItems;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.model.add.IAddTitleItems;
import com.petterp.latte_ec.model.home.IHomeTitleRvItems;
import com.petterp.latte_ec.view.add.topViewVp.RecordPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author by petterp
 * @date 2019-08-24
 */
@CreatePresenter(AddTopRvItemPresenter.class)
public class AddTopRvItemDelegate extends BaseFragment<AddTopRvItemPresenter> implements IAddTopRvItemView {
    @BindView(R2.id.add_top_rv_item_bar)
    Toolbar toolbar = null;
    @BindView(R2.id.vp_add_top_rv_item)
    ViewPager viewPager = null;
    @BindView(R2.id.tl_add_vp)
    TabLayout tabLayout = null;
    @BindView(R2.id.fb_add_top_rv_item)
    FloatingActionButton floatingActionButton = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_add_top_item;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        //注册CallBack
//        CallbackManager.getInstance().addCallback(RecordCallbackFields.ADD_RV_KIND, this);
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }

    @Override
    public void showView() {
        AddTopRvItemFragment consumeFragment = new AddTopRvItemFragment(getPresenter().consumeList());
        AddTopRvItemFragment incomeFragment = new AddTopRvItemFragment(getPresenter().incomeList());
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
        if (AddTopRvItemDelegateArgs.fromBundle(getArguments()).getMode().equals(IHomeTitleRvItems.CONSUME)) {
            viewPager.setCurrentItem(0);
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        } else {
            viewPager.setCurrentItem(1);
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                } else {
                    floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
