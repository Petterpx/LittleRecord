package com.petterp.latte_ec.main.home.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.petterp.latte_core.util.dpsityUtil.DensityUtil;
import com.petterp.latte_ec.R;

/**
 * Dialog delete
 *
 * @author by Petterp
 * @date 2019-07-29
 */
public class HomeDiaDelete extends DialogFragment {
    private IDialogTextListener deleteListener;

    public HomeDiaDelete(IDialogTextListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dia_home_delete, container);
        view.findViewById(R.id.tv_dia_home_delete).setOnClickListener(view1 -> deleteListener.ensure());
        view.findViewById(R.id.tv_dia_home_back).setOnClickListener(view1 -> deleteListener.back());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Window w = getDialog().getWindow();
        if (w != null) {
            WindowManager.LayoutParams lp = w.getAttributes();
            w.setGravity(Gravity.CENTER);
            w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            lp.width = (int) (DensityUtil.getScreenWidth(getActivity()) * 0.8);
            lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            w.setAttributes(lp);
        }
    }

}
