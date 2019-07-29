package com.petterp.latte_ec.model.add;

import android.graphics.Color;
import android.os.Bundle;

import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * add-model层
 * @author by Petterp
 */
public interface IAddModel {

    /**
     * 返回TitleConsumeRv数据
     */
    List<MultipleItemEntity> getConsumeRvList();

    /**
     * 返回TitleIncomeRv数据
     * @return
     */
    List<MultipleItemEntity> getIncomeRvList();

    /**
     * 返回BootomKeyRv数据
     * @return
     */
    List<MultipleItemEntity> getKeyRvList();


    /**
     * 设置当前为支出还是收入
     * @param mode
     */
    void setTitleMode(String mode);

    /**
     * 获取当前为支出还是收入
     * @return
     */
    String getTitleMode();

    /**
     * 设置Top Rv所选条目种类
     * @param
     */
    void setTitleRvKind(String[] kind);

    /**
     * 返回Top Rv所选条目种类
     * @return
     */
    String[] getTitleRvKind();


    /**
     * 设置保存按钮颜色
     * @param mode
     */
    void setKeyRvSaveColor(boolean mode);

    /**
     * 根据bundle判断操作状态
     * @param bundle
     */
    void setBundle(Bundle bundle);

    /**
     * 获取当前操作状态
     * @return
     */
    int getStateMode();

    /**
     * 获取要更新的值
     * @return
     */
    IAddBundleFields getStateUpdate();

}
