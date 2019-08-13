package com.petterp.latte_ec.model.analysis;

import com.github.mikephil.charting.charts.LineChart;
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

    List<MultipleItemEntity> classifyRvList();

    List<MultipleItemEntity> classifyRvItemList(String kind);

    String classifyPieKindMoney(String kind);
}
