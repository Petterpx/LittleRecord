package com.petterp.latte_core.delegates.bottom;

/**
 * @author Petterp on 2019/4/22
 * Summary:Bean类，存放底部图标+文字
 * email：1509492795@qq.com
 */
public final class BottomTabBean  {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon,CharSequence title){
        this.ICON=icon;
        this.TITLE=title;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
