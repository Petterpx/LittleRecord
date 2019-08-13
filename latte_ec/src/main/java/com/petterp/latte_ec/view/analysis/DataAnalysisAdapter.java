package com.petterp.latte_ec.view.analysis;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.util.dpsityUtil.DensityUtil;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.analysis.AnalyDiaFields;
import com.petterp.latte_ec.model.analysis.AnalysisFields;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-13
 */
public class DataAnalysisAdapter extends MultipleRecyclearAdapter {

    private DecimalFormat decimalFormat = new DecimalFormat("###################.##");

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DataAnalysisAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(DataAnalysisItemType.DATA_ANALYSIS_PIE_LIST, R.layout.item_analysis_pie_list);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
//        holder.setText(R.id.ic_analysis_pie_icon, entity.getField(MultipleFidls.NAME));
        holder.setText(R.id.tv_analysis_pie_kind, entity.getField(AnalysisFields.KIND));
        holder.setText(R.id.tv_analysis_pie_sum, entity.getField(AnalysisFields.SUM)+"笔");
        float money = entity.getField(AnalysisFields.MONEY);
        if (entity.getField(AnalysisFields.CATEGORY).equals("支出")) {
            money *= -1;
            holder.setTextColor(R.id.tv_analysis_pie_money, Color.RED);
        }
        holder.setText(R.id.tv_analysis_pie_money, String.valueOf(money));
        ProgressBar progressBar = holder.getView(R.id.view_analysis_pie);
        float scale = entity.getField(AnalysisFields.SCALE);
        progressBar.setProgress((int) (1000*scale));
        holder.setText(R.id.tv_analysis_pie_scale, decimalFormat.format(scale*100) + "%");
    }
}
