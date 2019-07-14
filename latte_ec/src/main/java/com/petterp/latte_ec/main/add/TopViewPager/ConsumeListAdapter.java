package com.petterp.latte_ec.main.add.TopViewPager;

import androidx.appcompat.widget.AppCompatTextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

public class ConsumeListAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected ConsumeListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ConsumeListItemType.ITEM_CONSUME_LIST, R.layout.item_vp_consume_list);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ConsumeListItemType.ITEM_CONSUME_LIST:
                IconTextView icon = holder.getView(R.id.tv_item_vp_consume_icon);
                AppCompatTextView title = holder.getView(R.id.tv_item_vp_consume_title);
                String titleVal = entity.getField(MultipleFidls.TITLE);
                String iconVal = entity.getField(MultipleFidls.NAME);
                icon.setText(iconVal);
                title.setText(titleVal);
                break;
            default:
                break;
        }
    }
}
