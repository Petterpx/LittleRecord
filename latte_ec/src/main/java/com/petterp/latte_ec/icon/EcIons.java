package com.petterp.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:引用第三方字体
 */
public enum EcIons implements Icon {
    //侧滑栏
    icon_kind('\ue81e'),
    icon_delete('\ue677'),
    icon_money('\ue68c'),
    icon_time('\ue6d3'),
    icon_update('\ue611'),

    //dialog
    icon_yun('\ue699'),
    icon_tongji('\ue6ac'),
    icon_jianyi('\ue615'),
    icon_shezhi('\ue69f'),
    icon_info('\ue60a'),

    //关于
    icon_git('\ue601'),
    icon_fenxiang('\ue608'),


    icon_qq('\ue505'),
    icon_zhiwen('\ue629'),


    //add
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
    icon_lijing('\ue707'),
    icon_huafei('\ue6ce'),
    icon_liwu('\ue61a'),
    icon_shuma('\ue502'),
    icon_gongzi('\ue606'),
    icon_yundong('\ue904'),
    icon_riyongping('\ue64b'),
    icon_shenghuofei('\ue678'),
    icon_yanjiu('\ue618'),
    icon_guanyu('\ue60a'),
    icon_dian('\ue612'),
    icon_yifu('\ue66c'),
    icon_qita('\ue742'),
    icon_gongjijing('\ue6ed'),
    icon_yuntongbu('\ue699'),
    icon_lvxing('\ue609'),
    icon_caidan('\ue6e9'),
    icon_meizhuang('\ue607'),
    icon_jiaotong('\ue693'),
    icon_zhufang('\ue61b'),
    icon_qingke('\ue61b'),
    icon_yule('\ue61a');


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
