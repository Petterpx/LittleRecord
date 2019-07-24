package com.petterp.latte_ec.presenter;

import com.petterp.latte_ec.model.home.IHomeImpl;
import com.petterp.latte_ec.model.home.IHomeModel;
import com.petterp.latte_ec.model.home.IHomeTitleFields;
import com.petterp.latte_ec.view.home.IHomeView;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * home-控制层
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

    private HashMap<IHomeTitleFields,String> getTitleinfo(){
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

    private void floatButtonListener(){
        if (iView != null) {
            iView.FloatButtonListener();
        }
    }


    public void showFlootButton(){
        if (iView != null) {
            iView.showFloatButton();
        }
    }

    /**
     * 隐藏flootbutton
     */
    public void hideFlootButton(){
        if (iView != null) {
            iView.hideFloatButton();
        }
    }


    //更新Rv内容
    public void updateRvView() {
        if (iView != null) {
            iView.updateRv();
        }
    }


    //更新Rv数据
    public void updateModel(MultipleItemEntity itemEntity, int position) {
        iModel.update(itemEntity, position);
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

}
