package com.petterp.latte_ec.main.add.topViewVp.rvItems;

import android.annotation.SuppressLint;

import androidx.appcompat.widget.AppCompatTextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.home.IHomeRvFields;
import com.petterp.latte_ec.main.add.topViewVp.RecordListItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public AddTopRvItemAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(RecordListItemType.ITEM_CONSUME_LIST, R.layout.item_vp_consume_list);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        if (holder.getItemViewType() == RecordListItemType.ITEM_CONSUME_LIST) {
            IconTextView icon = holder.getView(R.id.tv_item_vp_consume_icon);
            AppCompatTextView kind = holder.getView(R.id.tv_item_vp_consume_title);
            String kindVal = entity.getField(IHomeRvFields.KIND);
            String iconVal = entity.getField(MultipleFidls.NAME);
            icon.setText(iconVal);
            kind.setText(kindVal);
            icon.setTextColor(R.color.index_add_text_color);
            kind.setTextColor(R.color.index_add_text_color);
            icon.setBackgroundResource(R.drawable.item_vp_add_to);
        }
    }
}
