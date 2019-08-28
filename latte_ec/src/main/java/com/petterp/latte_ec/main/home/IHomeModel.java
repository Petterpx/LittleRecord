package com.petterp.latte_ec.main.home;

import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 首页-数据层
 *
 * @author by Petterp
 * @date 2019-07-23
 */
public interface IHomeModel {
    /**
     * 返回全部数据
     *
     * @return
     */
    List<MultipleItemEntity> getInfo();


    /**
     * 数据库查询
     */
    void queryInfo();


    /**
     * 获取Titlebar数据
     *
     * @return
     */
    HashMap<IHomeRvFields, String> getTitleInfo();


    /**
     * state -> update
     *
     * @param itemEntity
     */
    void update(MultipleItemEntity itemEntity);


    /**
     * state -delete
     *
     * @param itemEntity
     */
    void delete(MultipleItemEntity itemEntity);


    /**
     * state -> add
     *
     * @param itemEntity
     */
    void add(MultipleItemEntity itemEntity);


    /**
     * state -> addHeader
     *
     * @param itemEntity
     */
    void addHeader(MultipleItemEntity itemEntity);

    /**
     * 设置key
     *
     * @param key
     */
    void setKey(String key);

    String getKey();

    /**
     * 根据key查找Rv -> 设置 Header位置
     *
     * @param key
     */
    void setHeaderPosition(String key);


    /**
     * 设置 Header位置
     * @param headerPosition
     */
    void setHeaderPosition(int headerPosition);


    /**
     * 设置手指按下位置
     *
     * @param position
     */
    void setOndownPosition(int position);

    /**
     * 设置当前操作状态
     *
     * @param state
     */
    void setStateMode(int state);


    /**
     * 获取状态
     * @return
     */
    int getStateMode();

    /**
     * 设置新增数据位置
     *
     * @param position
     */
    void setAddPosition(int position);


    /**
     * 获取头像
     * @return url
     */
    String getDrawUserIcon();

    /**
     * 获取记录天数
     * @return
     */
    String getDrawRecord();

    /**
     * add更新时的数据情况
     * @param kind
     * @param kindNew
     * @param category
     */
    void updateItem(String kind,String kindNew,String category);

    /**
     * add删除时的数据情况
     * @param kind
     * @param category
     */
    void delegateItem(String kind,String category);

}
