package com.petterp.latte_ec.main.home;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * DrawerLayout 滑动监听
 *
 * @author by Petterp
 * @date 2019-07-25
 */
public class HomeDrawerListener implements DrawerLayout.DrawerListener {

    private Activity activity;
    private IHomeDrListener iHomeDrListener;

    HomeDrawerListener(Activity activity, IHomeDrListener iHomeDrListener) {
        this.activity = activity;
        this.iHomeDrListener = iHomeDrListener;
    }


    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        //获取屏幕的宽高
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
        iHomeDrListener.setHomeOffset(dm.widthPixels, dm.heightPixels);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
