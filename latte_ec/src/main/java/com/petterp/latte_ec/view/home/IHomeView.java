package com.petterp.latte_ec.view.home;

import com.petterp.latte_ec.model.home.IHomeTitleFields;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 首页-view层
 * @author by Petterp
 * @date 2019-07-23
 */
public interface IHomeView {


    /**
     * 设置内部内容
     */
    void setTitleinfo(HashMap<IHomeTitleFields,String> map);


    /**
     * 显示首页rv
     */
    void showRv(List<MultipleItemEntity> list);



    void FloatButtonListener();

    /**
     * 更新Rv内容
     */
    void updateRv();


    /**
     *floatButton点击事件
     */
    void hideFloatButton();

    void showFloatButton();
}
