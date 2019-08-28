package com.petterp.latte_ec.main.intro;

import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-16
 */
public class IntroAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public IntroAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(IntroItemType.INTRO_TYPE, R.layout.item_multiple_settings_text);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        holder.setText(R.id.text_single, entity.getField(MultipleFidls.TEXT));
        holder.setText(R.id.ic_icon_kind, entity.getField(MultipleFidls.NAME));
    }
}
