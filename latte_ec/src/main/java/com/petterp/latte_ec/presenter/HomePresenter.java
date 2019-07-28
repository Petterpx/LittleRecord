package com.petterp.latte_ec.presenter;

import android.util.Log;

import com.petterp.latte_ec.model.home.IHomeImpl;
import com.petterp.latte_ec.model.home.IHomeModel;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.view.home.IHomeView;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * home-控制层
 *
 * @author by Petterp
 * @date 2019-07-23
 */
public class HomePresenter {
    private IHomeModel iModel;
    private IHomeView iView;

    public HomePresenter(IHomeView iView) {
        this.iView = iView;
        iModel = new IHomeImpl();
    }

    /**
     * 初始化显示
     */
    public void showInfo() {
        iModel.queryInfo();
        showRvView();
        setTitleInfoView();
        floatButtonListener();
    }


    //显示titlebar具体内容
    private void setTitleInfoView() {
        if (iView != null) {
            iView.setTitleinfo(getTitleinfo());
        }
    }

    private HashMap<IHomeRvFields, String> getTitleinfo() {
        return iModel.getTitleInfo();
    }


    //显示首页rv
    private void showRvView() {
        if (iView != null) {
            iView.showRv(getRvInfo());
        }
    }

    //获取数据
    private List<MultipleItemEntity> getRvInfo() {
        return iModel.getInfo();
    }

    private void floatButtonListener() {
        if (iView != null) {
            iView.FloatButtonListener();
        }
    }


    //更新Rv内容
    public void updateRvView() {
        if (iView != null) {
            iView.updateRv();
        }
    }


    //更新Rv数据
    public void updateModel(MultipleItemEntity itemEntity) {
        iModel.update(itemEntity);
        //通知Rv刷新
        updateRvView();
        //通知TitleInfo刷新
        setTitleInfoView();
    }

    //删除Rv数据
    public void delegateModel(int position) {
        iModel.delegate(position);
    }

    //添加数据
    public void addModel(MultipleItemEntity itemEntity) {
        //model层添加数据
        iModel.add(itemEntity);
        //通知Rv刷新
        updateRvView();
        //通知TitleInfo刷新
        setTitleInfoView();
    }

    public void addHeaderModel(MultipleItemEntity itemEntity) {
        //设置key
        iModel.setKey(itemEntity.getField(IHomeRvFields.KEY));
        //model层添加数据
        iModel.addHeader(itemEntity);
        //通知Rv刷新
        updateRvView();
        //通知TitleInfo刷新
        setTitleInfoView();
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
     * 获得Header头位置
     *
     * @return
     */
    public int getHeaderPosition() {
        return iModel.getHeaderPosition();
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

    public String getKey() {
        return iModel.getKey();
    }
}
