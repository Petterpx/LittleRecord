package com.petterp.latte_ec.main.analysis.dia;

import android.graphics.Color;

import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-10
 */
public class DateRvAdapter extends MultipleRecyclearAdapter {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DateRvAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
//        int mode = entity.getField(MultipleFidls.ID);
//        if (mode == AnalyDiaFields.DIA_SELECT_YEAR) {
//            holder.setText(R.id.text_single, entity.getField(MultipleFidls.TEXT) + "年");
//            holder.setTextColor(R.id.text_single, Color.parseColor("#566974"));
//        } else {
        holder.setText(R.id.text_single, entity.getField(MultipleFidls.TEXT));
        if (entity.getField(MultipleFidls.TAG)) {
            holder.setTextColor(R.id.text_single, Color.parseColor("#566974"));
        } else {
            holder.setTextColor(R.id.text_single, Color.parseColor("#93A8B1"));
        }
//        }

    }
}
