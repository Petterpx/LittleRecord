package com.petterp.latte_ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.petterp.latte_ui.R;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/24
 * Summary:
 * email：1509492795@qq.com
 */
public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener){
        convenientBanner
                .setPages(new HolderCreator(), banners)
                //设置点的样式
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .startTurning(3000)
                .setCanLoop(true);
    }
}
