package com.petterp.latte_ec.main.home.dialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.home.IHomeRvFields;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * @author by Petterp
 * @date 2019-07-29
 */
public class HomeDiaQuery extends DialogFragment {

    private MultipleItemEntity itemEntity;
    private IHomeDiaQueryInfo info;

    public HomeDiaQuery(MultipleItemEntity itemEntity, IHomeDiaQueryInfo info) {
        this.itemEntity = itemEntity;
        this.info = info;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Window window = getDialog().getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_home_info);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            AppCompatTextView tvMoney = window.findViewById(R.id.tv_dia_money);
            AppCompatTextView tvKind = window.findViewById(R.id.tv_dia_kind);
            AppCompatTextView tvTime = window.findViewById(R.id.tv_dia_time);
            IconTextView icKind = window.findViewById(R.id.ic_dia_kind);

            tvMoney.setText("" + itemEntity.getField(IHomeRvFields.CONSUME_I));
            icKind.setText(itemEntity.getField(MultipleFidls.NAME));
            tvKind.setText(itemEntity.getField(IHomeRvFields.KIND));
            tvTime.setText(TimeUtils.build().getDateinfo(itemEntity.getField(IHomeRvFields.LONG_TIME)));
            window.findViewById(R.id.ic_dia_header_update).setOnClickListener(view -> info.update());
            window.findViewById(R.id.ic_dia_header_delete).setOnClickListener(view -> info.delete());
        }
    }
}
