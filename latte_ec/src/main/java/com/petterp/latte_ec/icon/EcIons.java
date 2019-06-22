package com.petterp.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:引用第三方字体
 */
public enum EcIons implements Icon {
    icon_scan('\ue618'),
    icon_news('\ue717'),
    icon_ali_pay('\ue65f');
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
