package com.petterp.latte_ec.main.add.topViewVp.rvItems;

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
import com.petterp.latte_core.util.Context.ToastUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.add.IAddTitleItems;
import com.petterp.latte_ec.main.home.IHomeTitleRvItems;
import com.petterp.latte_ec.main.add.AddItemFileds;
import com.petterp.latte_ec.main.add.topViewVp.RecordPagerAdapter;
import com.petterp.latte_ui.dialog.BaseDialogFragment;

import org.greenrobot.eventbus.EventBus;

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
    private BaseDialogFragment addDialogFragment;
    private String category = IHomeTitleRvItems.CONSUME;
    private AddTopRvItemFragment consumeFragment;
    private AddTopRvItemFragment incomeFragment;

    @Override
    public Object setLayout() {
        return R.layout.delegate_add_top_item;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        category = AddTopRvItemDelegateArgs.fromBundle(getArguments()).getMode();
        getPresenter().setTitleMode(category);
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }

    @Override
    public void showView() {
        consumeFragment = new AddTopRvItemFragment(getPresenter().consumeList());
        incomeFragment = new AddTopRvItemFragment(getPresenter().incomeList());
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
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4A90F2")));
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    category = IHomeTitleRvItems.CONSUME;
                    floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                } else {
                    category = IHomeTitleRvItems.INCOME;
                    floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4A90F2")));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        floatingActionButton.setOnClickListener(v -> showAddDialog());
    }

    @Override
    public void addView() {
        if (consumeFragment != null) {
            consumeFragment.addView();
        }
        if (incomeFragment != null) {
            incomeFragment.addView();
        }
    }

    @Override
    public void delegateView() {
        if (category.equals(IHomeTitleRvItems.CONSUME)){
            consumeFragment.delegateView();
        }else{
            incomeFragment.delegateView();
        }
    }


    private void showAddDialog() {
        if (addDialogFragment == null) {
            addDialogFragment = new BaseDialogFragment()
                    .Builder(getFragmentManager())
                    .setLayout(R.layout.dialog_edit)
                    .setWindowsView((view, viewHolder) -> {
                        viewHolder.setText(R.id.tv_dia_edit_title, "添加新分类", false);
                        viewHolder.setText(R.id.tv_dia_edit_ensure, "确定", true);
                        viewHolder.setText(R.id.tv_dia_edit_back, "取消", true);
                    })
                    .setOnClickListener((view, viewHolder) -> {
                        if (view.getId() == R.id.tv_dia_edit_ensure) {
                            String kind = viewHolder.getText(R.id.edit_dia);
                            if (kind.isEmpty()) {
                                ToastUtils.showCenterText("名字不能为null!");
                            } else {
                                EventBus.getDefault().post(new AddMessage(AddItemFileds.ADD_ITEM_ADD, kind, category));
                                viewHolder.dismiss();
                            }
                        } else {
                            viewHolder.dismiss();
                        }
                    });
        }
        addDialogFragment.show();
    }


}
