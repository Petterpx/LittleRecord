package com.petterp.latte_ec.view.analysis.dia;

import android.app.Dialog;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_ec.model.analysis.AnalyMessages;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;

/**
 * @author by petterp
 * @date 2019-08-10
 */
public class DateItemClcikListener extends SimpleClickListener {

    private Dialog dialog;
    private AnalyMessages messages;

    public DateItemClcikListener(Dialog dialog, AnalyMessages messages) {
        this.dialog = dialog;
        this.messages = messages;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEntity itemEntity= (MultipleItemEntity) adapter.getData().get(position);
        if (itemEntity.getField(MultipleFidls.TAG)){
            messages.setMonth(itemEntity.getField(MultipleFidls.TEXT));
            EventBus.getDefault().post(messages);
            dialog.dismiss();
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
