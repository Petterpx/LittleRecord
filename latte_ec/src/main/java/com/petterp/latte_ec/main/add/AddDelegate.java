package com.petterp.latte_ec.main.add;

import android.graphics.Rect;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.edittext.SoftKeyBoardListener;
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
    @BindView(R2.id.tl_add_vp)
    TabLayout tabLayout=null;
    @BindView(R2.id.edit_add_remark)
    AppCompatEditText editText=null;
    @BindView(R2.id.in_compile_item)
    LinearLayoutCompat layoutCompat=null;

    @OnClick(R2.id.it_index_add_back)
    void addBack(){
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
    }

    private void initViewPager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new ConsumeFragment());
        list.add(new ConsumeFragment());
        String [] sums={"支出","收入"};
        ConsumePagerAdapter adapter = new ConsumePagerAdapter(getChildFragmentManager(), list,sums);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTvColor(TextView textView) {

    }
}
