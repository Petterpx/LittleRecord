package com.petterp.latte_ec.view.home;

import com.petterp.latte_core.mvp.view.IBaseView;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 首页-view层
 * @author by Petterp
 * @date 2019-07-23
 */
public interface IHomeView extends IBaseView {


    /**
     * 设置内部内容
     */
    void setTitleinfo(HashMap<IHomeRvFields,String> map);


    /**
     * 显示首页rv
     */
    void showRv(List<MultipleItemEntity> list);


    /**
     * floatButton onlcik
     */
    void FloatButtonListener();

    /**
     * 更新Rv内容
     */
    void updateRv();

    /**
     * 初始化Draw
     */
    void showDrawInfo();


    /**
     * 更新侧滑信息
     */
    void updateDrawUser();

    /**
     * 更新侧滑天数
     */
    void updateDrawKeySum();

}
