package com.petterp.latte_ec.main.add.topViewVp.rvItems;

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

    boolean add(String kindNew, String category);

    void delegate(String kind, String category);

    void update(String kindNew, String kind, String category);

    void setTitleMode(String mode);
}
