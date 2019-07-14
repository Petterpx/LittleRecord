package com.petterp.latte_ec.main.add.TopViewPager;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

public class ConsumeItemClickListener  extends SimpleClickListener {
    private Context context;

    public ConsumeItemClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity= (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int id=entity.getField(MultipleFidls.ID);
        Toast.makeText(context, "点击了"+id, Toast.LENGTH_SHORT).show();
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
