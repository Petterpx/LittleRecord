package com.petterp.latte_ec.main.index;

import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;

import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;
import com.petterp.latte_ui.recyclear.MultipleViewHolder;

import java.util.List;

/**
 * @author Petterp on 2019/6/23
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class IndexAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected IndexAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(IndexItemType.INDEX_DETAIL_HEADER, R.layout.item_index_detail_header);
        addItemType(IndexItemType.INDEX_DETAIL_LIST, R.layout.item_index_detail_list);
    }

    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case IndexItemType.INDEX_DETAIL_HEADER:
                holder.setText(R.id.tv_index_rv_time, entity.getField(IndexFidls.TIME));
                holder.setText(R.id.tv_index_rv_day, entity.getField(IndexFidls.DAY));
                holder.setText(R.id.tv_index_rv_consume, entity.getField("支出:" + IndexFidls.CONSUME));
                break;
            case IndexItemType.INDEX_DETAIL_LIST:
                holder.setText(R.id.tv_index_rv_kind, entity.getField(IndexFidls.KIND));
                holder.setText(R.id.tv_index_rv_consume_i, entity.getField(IndexFidls.CONSUME_I));
                if (entity.getField(IndexFidls.MODE)) {
                    holder.setTextColor(R.id.it_index_rv_dot, Color.RED);
                } else {
                    holder.setTextColor(R.id.it_index_rv_dot, Color.GREEN);
                }
                break;
        }
    }

}
