package com.petterp.latte_ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.util.dpsityUtil.DensityUtil;
import com.petterp.latte_core.util.edittext.SoftHideBoardUtils;
import com.petterp.latte_ui.R;

/**
 * @author by petterp
 * @date 2019-08-06
 */
public class BaseDialogFragment extends DialogFragment {
    //点击事件回调
    private IonClickListener ionClickListener;
    //window view视图回调
    private IWindowsView iWindowsView;
    //布局
    private int layout;
    //Window view
    private Window window;
    //默认动画样式
    private int animations = AnimStyle.DEFAULT;

    private FragmentManager manager;

    public BaseDialogFragment Builder(FragmentManager manager) {
        this.manager = manager;
        return this;
    }


    /**
     * 点击事件接口回调
     */
    public interface IonClickListener {
        void diaOnclick(View view, ItemViewHolder viewHolder);
    }

    /**
     * view 回调
     */
    public interface IWindowsView {
        void diaWindowsView(Window view, ItemViewHolder viewHolder);
    }


    public BaseDialogFragment setOnClickListener(IonClickListener ionClickListener) {
        this.ionClickListener = ionClickListener;
        return this;
    }

    public BaseDialogFragment setWindowsView(IWindowsView iWindowsView) {
        this.iWindowsView = iWindowsView;
        return this;
    }

    public BaseDialogFragment setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public BaseDialogFragment setAnimations(@StyleRes int animations) {
        this.animations = animations;
        return this;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        window = getDialog().getWindow();
        if (window != null) {
            window.setContentView(layout);
            if (iWindowsView != null) {
                iWindowsView.diaWindowsView(window, getItemViewHolder());
            }
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(animations);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (DensityUtil.getScreenWidth(getActivity()) * 0.8);
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
        }
    }


    /**
     * 显示View
     */
    public BaseDialogFragment show() {
        show(manager, getClass().getName());
        return this;
    }

    /**
     * Dialog中弹出 Dialog
     * @param tag
     * @param fragmentManager
     * @return
     */


    /**
     * 获取ItemViewHolder,即内部view设置项
     *
     * @return
     */
    public ItemViewHolder getItemViewHolder() {
        return new ItemViewHolder();
    }


    public final class ItemViewHolder implements View.OnClickListener {
        /**
         * 根据Id设置Text,并是否添加点击事件
         *
         * @param id
         * @param res
         */
        public void setText(@IdRes int id, CharSequence res, boolean onclickMode) {
            TextView textView = window.findViewById(id);
            textView.setText(res);
            if (onclickMode) {
                textView.setOnClickListener(this);
            }
        }

        /**
         * 设置点击事件
         *
         * @param id
         */
        public void setOnclick(@IdRes int id) {
            window.findViewById(id).setOnClickListener(this);
        }

        /**
         * 根据Id设置Text Color
         *
         * @param viewId
         * @param textColor
         * @return
         */
        public void setTextColor(@IdRes int viewId, @ColorInt int textColor) {
            TextView view = window.findViewById(viewId);
            view.setTextColor(textColor);
        }

        /**
         * 根据Id 查找文本
         *
         * @param viewId
         * @return
         */
        public String getText(@IdRes int viewId) {
            TextView editText = window.findViewById(viewId);
            return editText.getText().toString().trim();
        }


        @Override
        public void onClick(View view) {
            ionClickListener.diaOnclick(view, this);
        }

        /**
         * 取消dialog
         */
        public void dismiss() {
            if (getDialog() != null && getDialog().isShowing()) {
                getDialog().dismiss();
                SoftHideBoardUtils.hidekey(Latte.getBaseActivity());
            }
        }
    }

}
