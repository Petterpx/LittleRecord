package com.petterp.latte_ec.presenter;


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
        showTitleInfo();
        floatButtonListener();
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
     * update
     *
     * @param itemEntity
     */
    public void updateModel(MultipleItemEntity itemEntity) {
        iModel.update(itemEntity);
        showUpdateInfo();
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

    //添加数据
    public void addModel(MultipleItemEntity itemEntity) {
        //model层添加数据
        iModel.add(itemEntity);
        showUpdateInfo();
    }

    public void addHeaderModel(MultipleItemEntity itemEntity) {
        //设置key
        iModel.setKey(itemEntity.getField(IHomeRvFields.KEY));
        //model层添加数据
        iModel.addHeader(itemEntity);
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

    public String getKey() {
        return iModel.getKey();
    }


}
