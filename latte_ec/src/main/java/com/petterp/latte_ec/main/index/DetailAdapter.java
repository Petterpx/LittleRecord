package com.petterp.latte_ec.main.index;

import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * @author Petterp on 2019/6/23
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class DetailAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected DetailAdapter(List<MultipleItemEntity> data) {
        super(data);
//        addItemType(IndexItemType.INDEX_ITEM, R.layout.item_index);
    }


}
