package com.petterp.latte_ec.view.add.topViewVp;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

public class RecordListAdapter extends MultipleRecyclearAdapter {
    private boolean mode = true;

    public RecordListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(RecordListItemType.ITEM_CONSUME_LIST, R.layout.item_vp_consume_list);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case RecordListItemType.ITEM_CONSUME_LIST:
                IconTextView icon = holder.getView(R.id.tv_item_vp_consume_icon);
                AppCompatTextView kind = holder.getView(R.id.tv_item_vp_consume_title);
                String kindVal = entity.getField(IHomeRvFields.KIND);
                String iconVal = entity.getField(MultipleFidls.NAME);
                icon.setText(iconVal);
                kind.setText(kindVal);
                icon.setTextColor(R.color.index_add_text_color);
                kind.setTextColor(R.color.index_add_text_color);
                icon.setBackgroundResource(R.drawable.item_vp_add_to);
                if (mode) {
                    if (entity.getField(MultipleFidls.ID).equals("0")) {
                        icon.setTextColor(Color.WHITE);
                        icon.setBackgroundResource(R.drawable.item_vp_add_up);
                        kind.setTextColor(Color.parseColor("#ff0099cc"));
                        mode = false;
                    }
                }
                break;
            default:
                break;
        }
    }


}
