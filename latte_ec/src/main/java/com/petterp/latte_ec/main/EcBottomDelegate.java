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
        items.put(new BottomTabBean("{fa-home}","明细"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-home}","分析"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-home}","我的"),new IndexDelegate());
        return builder.addItems(items).build();
    }

    /**
     * 默认显示页面
     * @return
     */
    @Override
    public int setIndexDelegate() {
        return 0;
    }

    /**
     * 点击之后的颜色
     * @return
     */
    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
