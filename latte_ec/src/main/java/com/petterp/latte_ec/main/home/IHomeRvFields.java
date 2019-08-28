package com.petterp.latte_ec.main.home;

/**
 * @author by Petterp
 * @date 2019-07-18
 */
public enum IHomeRvFields {
    //时间戳
    LONG_TIME,
    //具体时间
    TIME_INFO,
    //年-月-日
    YEAR_MONTH_DAY,
    //年-月
    YEAR_MONTH,
    MONTH_DAY,
    //周几
    DAY,
    //支出
    CONSUME,
    //收入
    INCOME,
    //结余
    SUR_PLUS,
    //Title Rv选择的条目
    KIND,
    //子支出(即日支出)
    CONSUME_I,
    //种类(收入/支出)
    CATEGORY,
    //备注
    REMARK,
    //Rv每个Header所对应的子条目长度
    HEADER_SUM,
    //日期Key
    KEY,
    MONEY,
    //同类型KIND个数
    SUM,
    //所占比例
    SCALE
}
