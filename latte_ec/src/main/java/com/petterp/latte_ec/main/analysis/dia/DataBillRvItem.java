package com.petterp.latte_ec.main.analysis.dia;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.petterp.latte_ec.R;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;
import java.util.Objects;

/**
 * @author by petterp
 * @date 2019-08-14
 */
public class DataBillRvItem extends DialogFragment {
    private List<MultipleItemEntity> list;

    public DataBillRvItem(List<MultipleItemEntity> list) {
        this.list = list;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Window window = getDialog().getWindow();
        if (window != null) {
            window.setContentView(R.layout.dia_analysis_bill);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            RecyclerView recyclerView = window.findViewById(R.id.rv_dia_analysis_bill);
            DataBillItemAdapter adapter = new DataBillItemAdapter(list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                    .color(Color.parseColor("#F0F1F2"))
                    .size(2)
                    .build().addTo(recyclerView);
        }
    }
}
