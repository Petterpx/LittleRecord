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
 * @date 2019-08-13
 */
public class DataClassifyItemAdapter extends MultipleRecyclearAdapter {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DataClassifyItemAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(DataAnalysisItemType.DATA_ANALYSIS_PIE_ITEM_LIST, R.layout.item_analysis_pie_dia);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        if (holder.getItemViewType() == DataAnalysisItemType.DATA_ANALYSIS_PIE_ITEM_LIST) {
            if (entity.getField(AnalysisFields.CATEGORY).equals("收入")) {
                holder.setTextColor(R.id.tv_analysis_dia_category, Color.GREEN);
                holder.setTextColor(R.id.tv_analysis_dia_money, Color.GREEN);
                holder.setText(R.id.tv_analysis_dia_money, "+" + entity.getField(AnalysisFields.MONEY));
            } else {
                holder.setText(R.id.tv_analysis_dia_money, "-" + entity.getField(AnalysisFields.MONEY));
            }
            holder.setText(R.id.tv_analysis_dia_kind, entity.getField(AnalysisFields.KIND));
            holder.setText(R.id.tv_analysis_dia_date, entity.getField(AnalysisFields.MONTH_DAY));
        }
    }
}
