package com.petterp.latte_ui.banner;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.petterp.latte_ui.R;

/**
 * @author Petterp on 2019/4/24
 * Summary:
 * email：1509492795@qq.com
 */
public class ImageHolder extends Holder<String> {

    private AppCompatImageView mImageView;
    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop()
            .fitCenter()
            ;

    public ImageHolder(View itemView) {
        super(itemView);
    }


    @Override
    protected void initView(View itemView) {
        mImageView=itemView.findViewById(R.id.banner_tu);
    }

    @Override
    public void updateUI(String data) {
        Glide.with(itemView)
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mImageView);
    }





}
