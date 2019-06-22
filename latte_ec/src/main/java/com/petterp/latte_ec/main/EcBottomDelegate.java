package com.petterp.latte_ec.main;

import android.graphics.Color;

import com.petterp.latte_core.delegates.bottom.BaseBottomDelegate;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_core.delegates.bottom.BottomTabBean;
import com.petterp.latte_core.delegates.bottom.ItemBuilder;
import com.petterp.latte_ec.main.index.IndexDelegate;

import java.util.LinkedHashMap;

/**
 * @author Petterp on 2019/6/21
 * Summary:底部导航栏超类
 * 邮箱：1509492795@qq.com
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items=new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","item1"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-home}","item2"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-home}","item3"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-home}","item4"),new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
