package com.petterp.latte_ui.launcher;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.petterp.latte_ui.R;

/**
 * @author Petterp on 2019/4/20
 * Summary:
 * email：1509492795@qq.com
 */
public class LauncherHolder extends Holder<Integer> {
    private AppCompatImageView mImageView=null;

    public LauncherHolder(View itemView) {
        super(itemView);
        mImageView=itemView.findViewById(R.id.cb_banner);
    }

    @Override
    protected void initView(View itemView) {
        mImageView=itemView.findViewById(R.id.cb_banner);
    }


    @Override
    public void updateUI(Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
