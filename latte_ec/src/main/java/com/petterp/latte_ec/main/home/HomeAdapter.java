package com.petterp.latte_ec.main.home;

import android.graphics.Color;

import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Petterp on 2019/6/23
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class HomeAdapter extends MultipleRecyclearAdapter {

    private DecimalFormat decimalFormat = new DecimalFormat("###################.##");

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected HomeAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(HomeItemType.HOME_DETAIL_HEADER, R.layout.item_index_detail_header);
        addItemType(HomeItemType.HOME_DETAIL_LIST, R.layout.item_index_detail_list);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case HomeItemType.HOME_DETAIL_HEADER:
                holder.setText(R.id.tv_index_rv_time, entity.getField(IHomeRvFields.YEAR_MONTH_DAY));
                holder.setText(R.id.tv_index_rv_day, entity.getField(IHomeRvFields.DAY));
                float consume = entity.getField(IHomeRvFields.CONSUME);
                holder.setText(R.id.tv_index_rv_consume, "支出: " + decimalFormat.format(Math.abs(consume)));
                break;
            case HomeItemType.HOME_DETAIL_LIST:
                holder.setText(R.id.tv_index_rv_kind, entity.getField(IHomeRvFields.KIND));
                holder.setText(R.id.tv_index_rv_consume_i,  decimalFormat.format(entity.getField(IHomeRvFields.CONSUME_I)));
                if (entity.getField(IHomeRvFields.CATEGORY).equals(IHomeTitleRvItems.CONSUME)) {
                    holder.setTextColor(R.id.it_index_rv_dot, Color.RED);
                } else {
                    holder.setTextColor(R.id.it_index_rv_dot, Color.GREEN);
                }
                break;
        }
    }

}
