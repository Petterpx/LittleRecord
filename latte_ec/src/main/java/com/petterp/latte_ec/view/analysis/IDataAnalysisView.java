package com.petterp.latte_ec.view.analysis;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.petterp.latte_core.mvp.view.IBaseView;
import com.petterp.latte_ec.model.analysis.AnalyInConsumeFields;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 数据分析View
 *
 * @author by petterp
 * @date 2019-08-08
 */
public interface IDataAnalysisView extends IBaseView {

    /**
     * 更新数据
     */
    void updateData(String date);

    void showLoader();

    /**
     * 设置收支总览的数据
     */
    void setInConsume(HashMap<AnalyInConsumeFields,String> map);

    /**
     * 设置每天收支波动图
     */
    void setDayInConsume(List<List<Entry>> lists,List<String> times);

    /**
     * 设置分类账单统计
     */
    void setClassifyBill(List<PieEntry> pieList, List< MultipleItemEntity> rvPieList);


    /**
     * 设置每日账单统计
     */
    void setDayBill();
}
