package com.petterp.latte_ec.view.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.model.add.IAddBundleFields;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.presenter.HomePresenter;
import com.petterp.latte_ec.view.add.AddDelegate;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.Objects;

/**
 * @author by Petterp
 * @date 2019-07-26
 */
public class HomeItemClickListener extends SimpleClickListener {
    private LatteDelegate mdelegate;
    private AlertDialog mDialog;
    private HomePresenter presenter;

    public HomeItemClickListener(LatteDelegate delegate, HomePresenter presenter) {
        this.mdelegate = delegate;
        this.presenter = presenter;
        mDialog = new AlertDialog.Builder(Objects.requireNonNull(delegate.getContext())).create();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        //如果点击位置为header，跳转添加页面
        if (entity.getItemType() == HomeItemType.HOME_DETAIL_HEADER) {
            //拿到子长度
            int sum = entity.getField(IHomeRvFields.HEADER_SUM);
            presenter.setStateMode(IHomeStateType.HEADER_ADD);
            //设置头位置
            presenter.setHeaderPosition(position);
            //设置添加位置
            presenter.setAddPosition(position + sum + 1);
            //设置key
            presenter.setKey(entity.getField(IHomeRvFields.KEY));
            mdelegate.getSupportDelegate().start(AddDelegate.newInstance());
        } else {
            IAddBundleFields fields = new IAddBundleFields(entity.getField(MultipleFidls.NAME),
                    entity.getField(IHomeRvFields.KIND),
                    entity.getField(IHomeRvFields.LONG_TIME),
                    entity.getField(IHomeRvFields.CATEGORY),
                    entity.getField(IHomeRvFields.CONSUME_I),
                    entity.getField(IHomeRvFields.REMARK),
                    entity.getField(IHomeRvFields.KEY));
            beginDialog(fields, position);
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

    @SuppressLint("SetTextI18n")
    public void beginDialog(IAddBundleFields fields, int position) {
        mDialog.show();
        final Window window = mDialog.getWindow();
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
            tvMoney.setText("" + fields.getMoney());
            icKind.setText(fields.getName());
            tvKind.setText(fields.getKind());
            tvTime.setText(TimeUtils.build().getDateinfo(fields.getTime()));
            window.findViewById(R.id.ic_dia_header_update).setOnClickListener(view -> {
                //设置header头位置，用于更新
                presenter.setHeaderPosition(fields.getKey());
                presenter.setOndownPosition(position);
                presenter.setStateMode(IHomeStateType.UPDATE);
                mDialog.dismiss();
                mdelegate.getSupportDelegate().start(AddDelegate.newInstance(fields));
            });
            window.findViewById(R.id.ic_dia_header_delete).setOnClickListener(view -> {

            });
        }
    }

}
