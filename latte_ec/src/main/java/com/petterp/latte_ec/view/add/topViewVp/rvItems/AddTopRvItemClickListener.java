package com.petterp.latte_ec.view.add.topViewVp.rvItems;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.view.add.topViewVp.RecordCallbackFields;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemClickListener extends SimpleClickListener {
    public int mode = 0;


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
//        CallbackManager.getInstance()
//                .getCallback(RecordCallbackFields.ADD_RV_KIND)
//                .executeCallback(new String[]{entity.getField(MultipleFidls.NAME),entity.getField(IHomeRvFields.KIND)});
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
