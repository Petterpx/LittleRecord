package com.petterp.latte_ec.main.analysis;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-10
 */
public interface IAnalysisModel {
    String getTitleRes();

    /**
     * 设置月份
     *
     * @param month
     */
    void setMonth(String month);

    /**
     * 设置年
     *
     * @param year
     */
    void setYear(String year);

    int getMonth();

    int getYear();

    /**
     * 查询全部数据
     */
    void queryInfo();


    /**
     * 获取收支总览的数据
     *
     * @return
     */
    HashMap<AnalyInConsumeFields, String> getInConsume();

    /**
     * 获取每天收支波动图
     *
     * @return
     */
    List<List<Entry>> getDaysInConsume();

    List<String> getTimes();


    /**
     * 获取分类账单MP
     *
     * @return
     */
    List<PieEntry> classifyPieChart();

    /**
     * 分类账单外围
     * @return
     */
    List<MultipleItemEntity> classifyRvList();

    List<MultipleItemEntity> classifyRvItemList(String kind);

    /**
     * 根据kind获取相应money
     * @param kind
     * @return
     */
    String classifyPieKindMoney(String kind);

    /**
     * 每日账单-外层Rv
     * @return
     */
    List<MultipleItemEntity> billRvList();

    /**
     * 每日平均支出
     * @return
     */
    float billScaleMoney();

    /**
     * 每日账单-内层Rv
     * @param date
     * @return
     */
    List<MultipleItemEntity> billRvItemList(String date);

    /**
     * 是否有数据
     * @return
     */
    boolean getDataMode();
}
