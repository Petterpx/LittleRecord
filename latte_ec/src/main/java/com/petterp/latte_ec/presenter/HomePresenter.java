package com.petterp.latte_ec.presenter;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.model.home.MessageItems;
import com.petterp.latte_ec.model.home.IHomeImpl;
import com.petterp.latte_ec.model.home.IHomeModel;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.view.home.IHomeView;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

/**
 * home-控制层
 *
 * @author by Petterp
 * @date 2019-07-23
 */
public class HomePresenter extends BasePresenter<IHomeView> {
    private IHomeModel iModel;
    private IHomeView iView;

    @Override
    public void getView(IHomeView view) {
        this.iView = view;
        iModel = new IHomeImpl();
        EventBus.getDefault().register(this);
    }


    /**
     * 初始化显示
     */
    public void showInfo() {
        iModel.queryInfo();
        showRvView();
        showTitleInfo();
        floatButtonListener();
        //初始化侧滑
        showDraw();
    }

    /**
     * 操作结束后的刷新
     */
    private void showUpdateInfo() {
        //更新Rv
        if (iView != null) {
            iView.updateRv();
        }
        //更新Title
        showTitleInfo();
    }

    /**
     * 显示Title
     */
    private void showTitleInfo() {
        //更新Title
        if (iView != null) {
            iView.setTitleinfo(getTitleinfo());
        }
    }


    /**
     * 获取Title全部数据
     *
     * @return
     */
    private HashMap<IHomeRvFields, String> getTitleinfo() {
        return iModel.getTitleInfo();
    }


    /**
     * 显示首页rv
     */
    private void showRvView() {
        if (iView != null) {
            iView.showRv(getRvInfo());
        }
    }


    /**
     * 获取数据
     *
     * @return
     */
    private List<MultipleItemEntity> getRvInfo() {
        return iModel.getInfo();
    }

    private void floatButtonListener() {
        if (iView != null) {
            iView.FloatButtonListener();
        }
    }



    /**
     * delete
     *
     * @param itemEntity
     */
    public void delegateModel(MultipleItemEntity itemEntity) {
        iModel.delete(itemEntity);
        showUpdateInfo();
    }

    /**
     * 设置Header 头位置
     *
     * @param key
     */
    public void setHeaderPosition(String key) {
        iModel.setHeaderPosition(key);
    }

    public void setHeaderPosition(int headerPosition) {
        iModel.setHeaderPosition(headerPosition);
    }


    /**
     * 设置手指按下位置
     *
     * @param position
     */
    public void setOndownPosition(int position) {
        iModel.setOndownPosition(position);
    }


    public int getStateMode() {
        return iModel.getStateMode();
    }

    /**
     * 设置当前状态
     *
     * @param state
     */
    public void setStateMode(int state) {
        iModel.setStateMode(state);
    }

    public void setAddPosition(int position) {
        iModel.setAddPosition(position);
    }

    public void setKey(String key) {
        iModel.setKey(key);
    }

    private String getKey() {
        return iModel.getKey();
    }

    private void showDraw() {
        if (iView != null) {
            iView.showDrawInfo();
        }
    }

    /**
     * 返回 User头像url
     *
     * @return
     */
    public String getDrawUserUrl() {
        return iModel.getDrawUserIcon();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        Log.e("demo", "ondey");
    }

    public String getDrawRecord() {
        return iModel.getDrawRecord();
    }

    /**
     * EvenBus 接收Add传回的具体item
     *
     * @param messageItems
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modeState(MessageItems messageItems) {
        if (messageItems.getMode() == 1) {
            if (iView != null) {
                iView.updateDrawUser();
            }
        } else {
            MultipleItemEntity itemEntity = messageItems.getItemEntity();
            itemEntity.setFild(IHomeRvFields.KEY, getKey());
            switch (getStateMode()) {
                case IHomeStateType.ADD:
                    iModel.add(itemEntity);
                    break;
                case IHomeStateType.UPDATE:
                    iModel.update(itemEntity);
                    break;
                case IHomeStateType.HEADER_ADD:
                    //设置key
                    iModel.setKey(itemEntity.getField(IHomeRvFields.KEY));
                    //model层添加数据
                    iModel.addHeader(itemEntity);
                    break;
                default:
                    break;
            }
            showUpdateInfo();
            if (iView != null) {
                iView.updateDrawKeySum();
            }
        }
    }

}
