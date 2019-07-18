package com.petterp.latte_ec.main.index.add.TopViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.index.IndexFidls;
import com.petterp.latte_ec.main.index.add.Ikind;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

public class RecordItemClickListener extends SimpleClickListener {
    private Context context;
    public int mode = 0;
    private Ikind ikind;

    public RecordItemClickListener(Context context, Ikind ikind) {
        this.context = context;
        this.ikind = ikind;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        adapter.notifyItemChanged(mode);
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        IconTextView iconTextView = view.findViewById(R.id.tv_item_vp_consume_icon);
        AppCompatTextView textView = view.findViewById(R.id.tv_item_vp_consume_title);
        iconTextView.setBackgroundResource(R.drawable.item_vp_add_up);
        iconTextView.setTextColor(Color.WHITE);
        textView.setTextColor(Color.parseColor("#ff0099cc"));
        mode = position;
        ikind.kindData(entity.getField(IndexFidls.KIND));
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
