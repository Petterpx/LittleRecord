package com.petterp.latte_ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.petterp.latte_ui.R;

/**
 * @author Petterp on 2019/4/20
 * Summary:相当于Adapter
 * email：1509492795@qq.com
 */
public class LauncherHolderCreator implements CBViewHolderCreator {

    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.banner_layout;
    }
}
