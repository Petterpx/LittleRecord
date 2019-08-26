package com.petterp.latte_ec.view.add.topViewVp.rvItems;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ui.dialog.BaseDialogFragment;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemClickListener extends SimpleClickListener {
    public int mode = 0;
    private FragmentManager manager;
    private BaseDialogFragment itemDialog;
    private BaseDialogFragment itemChildDialog;
    private BaseDialogFragment itemChildDialogDelegate;
    private MultipleItemEntity entity;
    public AddTopRvItemClickListener(FragmentManager manager) {
        this.manager = manager;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        adapter.notifyItemChanged(mode);
        entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        IconTextView iconTextView = view.findViewById(R.id.tv_item_vp_consume_icon);
        AppCompatTextView textView = view.findViewById(R.id.tv_item_vp_consume_title);
        iconTextView.setBackgroundResource(R.drawable.item_vp_add_up);
        iconTextView.setTextColor(Color.WHITE);
        textView.setTextColor(Color.parseColor("#ff0099cc"));
        mode = position;
        showDialog();
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

    private void showDialog() {
        if (itemDialog == null) {
            itemDialog = new BaseDialogFragment().Builder(manager)
                    .setLayout(R.layout.dialog_rv_item_delegate)
                    .setWindowsView((view, viewHolder) -> {
                        viewHolder.setOnclick(R.id.tv_dia_update);
                        viewHolder.setOnclick(R.id.tv_dia_delegate);
                    })
                    .setOnClickListener((view, viewHolder) -> {
                        if (view.getId() == R.id.tv_dia_update) {
                            showUpdateDialog();
                        } else {

                        }
                        viewHolder.dismiss();
                    });
        }
        itemDialog.show();
    }

    private void showUpdateDialog() {
        if (itemChildDialog == null) {
            itemChildDialog = new BaseDialogFragment()
                    .Builder(manager)
                    .setLayout(R.layout.dialog_edit)
                    .setWindowsView((view, viewHolder) -> {
                        viewHolder.setText(R.id.tv_dia_edit_title, "修改别名", false);
                        viewHolder.setText(R.id.tv_dia_edit_ensure, "确定", true);
                        viewHolder.setText(R.id.tv_dia_edit_back, "取消", true);
                    })
                    .setOnClickListener((view, viewHolder) -> {
                        if (view.getId() == R.id.tv_dia_edit_ensure) {
                            String kind = viewHolder.getText(R.id.edit_dia);
                            if (kind.isEmpty()) {
                                Toast.makeText(Latte.getContext(), "别名不能为null!", Toast.LENGTH_SHORT).show();
                            } else {
                                entity.setFild(IHomeRvFields.KIND, kind);
                                baseQuickAdapter.notifyItemChanged(mode);
                            }
                        }
                        viewHolder.dismiss();
                    });
        }
        itemChildDialog.show();
    }



}
