package com.petterp.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:引用第三方字体
 */
public enum EcIons implements Icon {
    icon_kind('\ue622'),
    icon_delete('\ue627'),
    icon_money('\ue658'),
    icon_time('\ue603'),
    icon_update('\ue71b');
    private char character;

    EcIons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
