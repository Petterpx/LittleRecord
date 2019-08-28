package com.petterp.latte_ec.main.analysis.dia;

import android.graphics.Color;

import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.analysis.AnalysisFields;
import com.petterp.latte_ec.main.analysis.DataAnalysisItemType;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-14
 */
public class DataBillItemAdapter extends MultipleRecyclearAdapter {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DataBillItemAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(DataAnalysisItemType.DATA_BILL_RV_ITEM_LIST, R.layout.arrow_dia_anaylsis_bill);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        if (holder.getItemViewType() == DataAnalysisItemType.DATA_BILL_RV_ITEM_LIST) {
            if (entity.getField(AnalysisFields.CATEGORY).equals("收入")) {
                holder.setTextColor(R.id.tv_analysis_dia_bill_category, Color.GREEN);
                holder.setTextColor(R.id.tv_analysis_dia_bill_money, Color.GREEN);
                holder.setText(R.id.tv_analysis_dia_bill_money, "+" + entity.getField(AnalysisFields.MONEY));
            } else {
                holder.setText(R.id.tv_analysis_dia_bill_money, "-" + entity.getField(AnalysisFields.MONEY));
            }
            holder.setText(R.id.tv_dia_analysis_bill_kind, entity.getField(AnalysisFields.KIND));
        }
    }
}
