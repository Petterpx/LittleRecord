package com.petterp.latte_ec.view.add.topViewVp.rvItems;

import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;

/**
 * @author Petterp on 2019/8/24
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public interface IAddTopRvItemModel {
    List<MultipleItemEntity> consumeList();
    List<MultipleItemEntity> incomeList();

    void queryInfo();

    void updateKind(String kindNew,String kind,String category);

    void addKind(String kind,String category);

    void delegate(String kind,String category);
}
