package com.petterp.latte_ui.banner;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.petterp.latte_ui.R;

/**
 * @author Petterp on 2019/4/24
 * Summary:
 * email：1509492795@qq.com
 */
public class HolderCreator implements CBViewHolderCreator {
    @Override
    public Holder createHolder(View itemView) {
        return new ImageHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_multipe_banner_layout;
    }
}
