package com.petterp.latte_ec.view.home.dialog;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author by Petterp
 * @date 2019-07-26
 */
public class DialogAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DialogAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(HomeDiaItemType.DIA_HOME_HEADER, R.layout.item_dia_header_list);
        addItemType(HomeDiaItemType.DIA_HOME_MONEY, R.layout.item_dia_money_list);
        addItemType(HomeDiaItemType.DIA_HOME_KIND, R.layout.item_dia_kind_list);
        addItemType(HomeDiaItemType.DIA_HOME_TIME, R.layout.item_dia_time_list);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case HomeDiaItemType.DIA_HOME_HEADER:
                IconTextView icDiaUpdate=holder.getView(R.id.ic_dia_header_update);
                icDiaUpdate.setOnClickListener(view -> {

                });
                IconTextView icDiaDelete=holder.getView(R.id.ic_dia_header_delete);
                icDiaDelete.setOnClickListener(view -> {

                });
                break;
            case HomeDiaItemType.DIA_HOME_MONEY:
                holder.setText(R.id.tv_dia_rv_money, ""+entity.getField(IHomeRvFields.CONSUME_I));
                break;
            case HomeDiaItemType.DIA_HOME_KIND:
                holder.setText(R.id.ic_dia_rv_kind, entity.getField(MultipleFidls.NAME));
                holder.setText(R.id.tv_dia_rv_kind, entity.getField(IHomeRvFields.KIND));
                break;
            case HomeDiaItemType.DIA_HOME_TIME:
                holder.setText(R.id.tv_dia_rv_time, TimeUtils.build().getDateinfo(entity.getField(IHomeRvFields.LONG_TIME)));
                break;
            default:
                break;

        }
    }
}
