package com.petterp.latte_ec.main.intro;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mob.MobSDK;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_ec.R;

import cn.sharesdk.onekeyshare.OnekeyShare;

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
        }else{
            showShare(null);
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

    private void showShare(String platform) {
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("宁小记");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("https://github.com/Petterpx/LittleRecord/blob/master/README.md");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("你的记账好帮手。");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        //启动分享
        oks.show(MobSDK.getContext());
    }
}
