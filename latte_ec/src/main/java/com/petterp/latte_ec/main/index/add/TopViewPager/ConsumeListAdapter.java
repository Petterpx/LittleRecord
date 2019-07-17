package com.petterp.latte_ec.main.index.add.TopViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

public class ConsumeListAdapter extends MultipleRecyclearAdapter {
    private boolean mode = true;

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

    @SuppressLint("ResourceAsColor")
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
                icon.setTextColor(R.color.index_add_text_color);
                title.setTextColor(R.color.index_add_text_color);
                icon.setBackgroundResource(R.drawable.item_vp_add_to);
                if (mode) {
                    if (entity.getField(MultipleFidls.ID).equals("0")) {
                        icon.setTextColor(Color.WHITE);
                        icon.setBackgroundResource(R.drawable.item_vp_add_up);
                        title.setTextColor(Color.parseColor("#ff0099cc"));
                        mode = false;
                    }
                }
                break;
            default:
                break;
        }
    }


}
