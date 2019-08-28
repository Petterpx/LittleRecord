package com.petterp.latte_ec.main.add.BootomCompile;

import android.graphics.Color;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author by Petterp
 * @date 2019-07-16
 */
public class CompileListAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CompileListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(CompileListItemType.ITEM_COMPILE, R.layout.item_add_compile_list);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case CompileListItemType.ITEM_COMPILE:
                IconTextView icon = holder.getView(R.id.ic_add_compile_list);
                String iconVal = entity.getField(MultipleFidls.NAME);
                icon.setText(iconVal);
                if (iconVal.equals("保存")) {
                    if (entity.getField(MultipleFidls.TAG)) {
                        icon.setBackgroundColor(Color.parseColor("#F93A30"));
                    }else{
                        icon.setBackgroundColor(Color.parseColor("#06F985"));
                    }
                    icon.setTextColor(Color.WHITE);
                }
                break;
            default:
                break;
        }
    }
}
