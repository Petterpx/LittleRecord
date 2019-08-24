package com.petterp.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:引用第三方字体
 */
public enum EcIons implements Icon {
//    icon_kind('\ue622'),
//    icon_delete('\ue627'),
//    icon_money('\ue658'),
//    icon_time('\ue603'),
//    icon_update('\ue71b'),
    icon_back('\ue6d2'),
    icon_lingshi('\ue681'),
    icon_jiayou('\ue602'),
    icon_waikuai('\ue653'),
    icon_hongbao('\ue614'),
    icon_xuexi('\ue660'),
    icon_sancan('\ue620'),
    icon_yiliao('\ue610'),
    icon_cuowu('\ue62e'),
    icon_haizi('\ue5b9'),
    icon_lijin('\ue707'),
    icon_huafei('\ue6ce'),
    icon_qq('\ue505'),
    icon_liwu('\ue61a'),
    icon_shuma('\ue502'),
    icon_gongzi('\ue606'),
    icon_git('\ue601'),
    icon_yundong('\ue904'),
    icon_riyong('\ue64b'),
    icon_shenghuofei('\ue678'),
    icon_yanjiu('\ue618'),
    icon_guanyu('\ue60a'),
    icon_dian('\ue612'),
    icon_yifu('\ue66c'),
    icon_qita('\ue742'),
    icon_jianyi('\ue615'),
    icon_gongjijing('\ue6ed'),
    icon_yuntongbu('\ue699'),
    icon_tongji('\ue6ac'),
    icon_fenxiang('\ue608'),
    icon_lvxing('\ue609'),
    icon_shezhi('\ue69f'),
    icon_caidan('\ue6e9'),
    icon_meizhuang('\ue607');


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
