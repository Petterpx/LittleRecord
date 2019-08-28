package com.petterp.latte_ec.main.home.draw;

import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author by Petterp
 * @date 2019-07-30
 */
public class DrawAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DrawAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(DrawFields.DRAW_HOME_FILEDS, R.layout.item_draw_text);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        if (holder.getItemViewType() == DrawFields.DRAW_HOME_FILEDS) {
            holder.setText(R.id.ic_draw_rv_item,entity.getField(MultipleFidls.NAME));
            holder.setText(R.id.tv_draw_rv_item,entity.getField(MultipleFidls.TEXT));
        }
    }
}
