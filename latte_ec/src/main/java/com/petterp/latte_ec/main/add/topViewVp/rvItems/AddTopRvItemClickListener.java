package com.petterp.latte_ec.main.add.topViewVp.rvItems;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.util.Context.ToastUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.home.IHomeRvFields;
import com.petterp.latte_ec.main.add.AddItemFileds;
import com.petterp.latte_ui.dialog.BaseDialogFragment;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;

/**
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemClickListener extends SimpleClickListener {
    public int mode = 0;
    private FragmentManager manager;
    private BaseDialogFragment itemDialog;
    private BaseDialogFragment itemChildDialog;
    private BaseDialogFragment itemChildDelehate;
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
                            showDelegateDialog();
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
                            String kindNew = viewHolder.getText(R.id.edit_dia);
                            if (kindNew.isEmpty()) {
                                Toast.makeText(Latte.getContext(), "别名不能为null!", Toast.LENGTH_SHORT).show();
                            } else {
                                final String kind = entity.getField(IHomeRvFields.KIND);
                                if (kind.equals("其他")){
                                    ToastUtils.showCenterText("默认分类无法更改");
                                }else{
                                    final String category = entity.getField(IHomeRvFields.CATEGORY);
                                    entity.setFild(IHomeRvFields.KIND, kindNew);
                                    baseQuickAdapter.notifyItemChanged(mode);
                                    EventBus.getDefault().post(new AddMessage(AddItemFileds.ADD_ITEM_UPDATE, kind, kindNew, category, mode));
                                }

                            }
                        }
                        viewHolder.dismiss();
                    });
        }
        itemChildDialog.show();
    }

    private void showDelegateDialog() {
        if (itemChildDelehate == null) {
            itemChildDelehate = new BaseDialogFragment()
                    .Builder(manager)
                    .setLayout(R.layout.dialog_rv_item_select)
                    .setWindowsView((view, viewHolder) -> {
                        viewHolder.setText(R.id.tv_dia_title, "删除分类", false);
                        viewHolder.setText(R.id.tv_dia_delegate, "确定", true);
                        viewHolder.setText(R.id.tv_dia_cancel, "取消", true);
                        TextView message = view.findViewById(R.id.tv_dia_message);
                        SpannableStringBuilder style = new SpannableStringBuilder(
                                "如果您的账单中 包含 此分类,如果删除，会将账单的分类改为 其他 ,确定吗？");
                        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
                        style.setSpan(span, 8, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        style.setSpan(span, 30, 32, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        message.setText(style);
                    })
                    .setOnClickListener((view, viewHolder) -> {
                        if (view.getId() == R.id.tv_dia_delegate) {
                            String kind = entity.getField(IHomeRvFields.KIND);
                            if (kind.equals("其他")) {
                                ToastUtils.showCenterText("默认分类无法更改");
                            } else {
                                String category = entity.getField(IHomeRvFields.CATEGORY);
                                baseQuickAdapter.getData().remove(mode);
                                baseQuickAdapter.notifyDataSetChanged();
                                EventBus.getDefault().post(new AddMessage(AddItemFileds.ADD_ITEM_DELEGATE, kind, category));
                            }
                        }
                        viewHolder.dismiss();
                    });
        }
        itemChildDelehate.show();
    }
}
