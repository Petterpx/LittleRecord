package com.petterp.latte_ec.main.home;

/**
 * DrawerLayout滑动监听
 *
 * @author by Petterp
 * @date 2019-07-25
 */
public interface IHomeDrListener {
    /**
     * @param r 子视图right距父布局右边距 距离，简单理解为 子视图right+子视图宽度
     * @param b 子视图bottom距父布局下边距 距离 简单理解为 子视图top+子视图高度
     */
    void setHomeOffset(int r, int b);
}
