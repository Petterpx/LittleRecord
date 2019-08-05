package com.petterp.latte_ec.view.home.draw;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * @author by Petterp
 * @date 2019-07-30
 */
public class DrawItemClickListener extends SimpleClickListener {

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEntity itemEntity = (MultipleItemEntity) adapter.getData().get(position);
        int id = itemEntity.getField(MultipleFidls.ID);
        switch (id) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
