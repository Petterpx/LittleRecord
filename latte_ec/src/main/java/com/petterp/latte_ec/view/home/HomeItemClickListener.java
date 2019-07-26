package com.petterp.latte_ec.view.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.view.home.dialog.DialogAdapter;
import com.petterp.latte_ec.view.home.dialog.HomeDiaItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author by Petterp
 * @date 2019-07-26
 */
public class HomeItemClickListener extends SimpleClickListener {
    private Context context;
    private AlertDialog mDialog;

    public HomeItemClickListener(Context context) {
        this.context = context;
        mDialog = new AlertDialog.Builder(context).create();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        List<MultipleItemEntity> list = new ArrayList<>();
        MultipleItemEntity header = MultipleItemEntity.builder().setItemType(HomeDiaItemType.DIA_HOME_HEADER).build();
        MultipleItemEntity money = MultipleItemEntity
                .builder()
                .setItemType(HomeDiaItemType.DIA_HOME_MONEY)
                .setField(IHomeRvFields.CONSUME_I, entity.getField(IHomeRvFields.CONSUME_I)).build();
        MultipleItemEntity kind = MultipleItemEntity
                .builder()
                .setItemType(HomeDiaItemType.DIA_HOME_KIND)
                .setField(MultipleFidls.NAME, entity.getField(MultipleFidls.NAME))
                .setField(IHomeRvFields.KIND, entity.getField(IHomeRvFields.KIND))
                .build();
        MultipleItemEntity time = MultipleItemEntity
                .builder()
                .setItemType(HomeDiaItemType.DIA_HOME_TIME)
                .setField(IHomeRvFields.LONG_TIME, entity.getField(IHomeRvFields.LONG_TIME))
                .build();
        Log.e("Demo","time-long"+entity.getField(IHomeRvFields.LONG_TIME));
        Log.e("Demo","money"+entity.getField(IHomeRvFields.CONSUME_I));
        Log.e("Demo","name"+entity.getField(MultipleFidls.NAME)+"----KIND"+entity.getField(IHomeRvFields.KIND));
        list.add(header);
        list.add(money);
        list.add(kind);
        list.add(time);
        beginDialog(list);
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

    public void beginDialog(List<MultipleItemEntity> list) {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_home_rv);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            RecyclerView recyclerView = window.findViewById(R.id.rv_home_dia);
            DialogAdapter adapter = new DialogAdapter(list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //设置Rv分割线
            RecyclerViewDivider.with(Objects.requireNonNull(context))
                    .color(Color.parseColor("#F0F1F2"))
                    .size(2)
                    .build().addTo(recyclerView);
        }
    }
}
