package com.petterp.latte_ec.view.intro;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;

/**
 * @author by petterp
 * @date 2019-08-17
 */
public class IntroItemClcikListener extends SimpleClickListener {
    BaseFragment fragment;

    public IntroItemClcikListener(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position==0){
            fragment.fragmentStart(R.id.action_introDelegate_to_introWebDelegate);
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
