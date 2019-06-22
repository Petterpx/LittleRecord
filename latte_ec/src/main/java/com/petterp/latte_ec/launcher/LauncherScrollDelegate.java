package com.petterp.latte_ec.launcher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.petterp.latte_core.app.AccouttManager;
import com.petterp.latte_core.app.IUserCheker;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ec.R;
import com.petterp.latte_ui.launcher.ILauncherListener;
import com.petterp.latte_ui.launcher.LauncherHolderCreator;
import com.petterp.latte_ui.launcher.OnLauncherFinishTag;
import com.petterp.latte_ui.launcher.ScrollLauncherTag;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/20
 * Summary:轮播图片类
 * email：1509492795@qq.com
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvententBanner=null;
    private static  final ArrayList<Integer> INTEGERS=new ArrayList<>();
    private ILauncherListener mILauncherListener=null;

    /**
     * 加载图片轮播
     */
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvententBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                //设置底部小圆点
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                //水平居中
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置点击事件
                .setOnItemClickListener(this)
                //设置无限轮播
                .setCanLoop(false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ILauncherListener){
            mILauncherListener= (ILauncherListener) context;
        }
    }

    @Override
    public Object setLayout() {
        mConvententBanner =new ConvenientBanner<Integer>(getContext());
        return mConvententBanner;
    }

    /**
     * view加载完成后调用此方法
     * @param savedInstanceState
     * @param view
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        initBanner();
    }

    /**
     * 点击图片的反应功能(判断是否是第一次启动)
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position==INTEGERS.size()-1){
            LatterPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);

            //检查用户是否已经登录
            //检查用户是否登录了App
            AccouttManager.checkAccount(new IUserCheker() {
                @Override
                //有用户信息
                public void onSignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                //没有用户信息
                public void onNotSoignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

}
