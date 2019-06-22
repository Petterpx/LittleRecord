package com.petterp.latte_core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * @author Petterp on 2019/4/22
 * Summary:建造者->完成数据的建造
 * email：1509492795@qq.com
 */
public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS=new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }
    public  final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
