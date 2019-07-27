package com.petterp.latte_ec.model.home;

import android.os.Bundle;

import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 首页-数据层
 * @author by Petterp
 * @date 2019-07-23
 */
public interface IHomeModel {
    //提供数据
    List<MultipleItemEntity> getInfo();

    //查询数据库数据
    void queryInfo();

    //修改数据
    void update(MultipleItemEntity itemEntity);

    //删除数据
    void delegate(int position);

    //新增数据
    void add(MultipleItemEntity itemEntity);

    /**
     * 获取Titlebar数据
     * @return
     */
    HashMap<IHomeRvFields,String> getTitleInfo();


    /**
     * 在Handler上添加
     * @param itemEntity
     */
    void addHeader(MultipleItemEntity itemEntity);

    /**
     * 设置key
     * @param key
     */
    void setKey(String key);

    String getKey();

    /**
     * 根据key查找Rv 中相应Header位置
     * @param key
     */
    void setHeaderPosition(String key);

    void setHeaderPosition(int headerPosition);

    int getHeaderPosition();

    void setOndownPosition(int position);

    void setStateMode(int state);

    int getStateMode();

    void setAddPosition(int position);
}
