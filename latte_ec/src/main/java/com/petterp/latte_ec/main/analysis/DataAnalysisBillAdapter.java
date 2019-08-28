package com.petterp.latte_ec.main.analysis;

import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-14
 */
public class DataAnalysisBillAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DataAnalysisBillAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(DataAnalysisItemType.DATA_BILL_RV_LIST, R.layout.item_analysis_bill_rv);
    }
    private DecimalFormat decimalFormat = new DecimalFormat("###################.##");


    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        holder.setText(R.id.tv_analysis_bill_date, entity.getField(AnalysisFields.YEAR_MONTH_DAY));
        holder.setText(R.id.tv_analysis_bill_sum, entity.getField(AnalysisFields.SUM)+ "笔");
        float income = entity.getField(AnalysisFields.INCOME_MONEY);
        if (Math.abs(income) > 0) {
            holder.setVisible(R.id.tv_analysis_bill_income, true);
            holder.setText(R.id.tv_analysis_bill_income, "+" + decimalFormat.format(income));
        }
        float consume = entity.getField(AnalysisFields.CONSUME_MONEY);
        if (Math.abs(consume) > 0) {
            holder.setVisible(R.id.tv_analysis_bill_consume, true);
            holder.setText(R.id.tv_analysis_bill_consume, decimalFormat.format(consume));
        }
    }
}
