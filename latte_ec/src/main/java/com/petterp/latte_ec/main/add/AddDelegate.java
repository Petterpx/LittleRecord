package com.petterp.latte_ec.main.add;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.add.TopViewPager.ConsumeFragment;
import com.petterp.latte_ec.main.add.TopViewPager.ConsumePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加
 *
 * @author Petterp
 */
public class AddDelegate extends LatteDelegate implements ViewPager.OnPageChangeListener {
    @BindView(R2.id.index_bar_add)
    Toolbar toolbar = null;
    @BindView(R2.id.vp_index_add)
    ViewPager viewPager = null;
    @BindView(R2.id.text_index_add_consume)
    TextView tvConsume = null;
    @BindView(R2.id.text_index_add_income)
    TextView tvIncome = null;
    @OnClick(R2.id.delegate_index_add_back)
    void addBack(){
        getSupportDelegate().pop();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_add;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        List<Fragment> list = new ArrayList<>();
        list.add(new ConsumeFragment());
        list.add(new ConsumeFragment());
        ConsumePagerAdapter adapter = new ConsumePagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            setTvColor(tvConsume);
        } else {
            setTvColor(tvIncome);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTvColor(TextView textView) {
        tvConsume.setBackgroundColor(getResources().getColor(R.color.app_title_color));
        tvIncome.setBackgroundColor(getResources().getColor(R.color.app_title_color));
        textView.setBackgroundResource(R.drawable.delegate_add_up);
    }
}
