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
import com.petterp.latte_ec.view.home.dialog.HomeDiaDelete;
import com.petterp.latte_ec.view.home.dialog.HomeDiaQuery;
import com.petterp.latte_ec.view.home.dialog.IHomeDiaDeleteListener;
import com.petterp.latte_ec.view.home.dialog.IHomeDiaQueryInfo;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.Objects;

/**
 * @author by Petterp
 * @date 2019-07-26
 */
public class HomeItemClickListener extends SimpleClickListener implements IHomeDiaDeleteListener, IHomeDiaQueryInfo {
    private LatteDelegate mdelegate;
    private HomePresenter presenter;
    private MultipleItemEntity entity;
    private int position;
    private HomeDiaQuery diaQuery;
    private HomeDiaDelete diaDelete;

    public HomeItemClickListener(LatteDelegate delegate, HomePresenter presenter) {
        this.mdelegate = delegate;
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        entity = (MultipleItemEntity) adapter.getData().get(position);
        this.position = position;
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
            diaQuery = new HomeDiaQuery(entity, this);
            diaQuery.show(mdelegate.getChildFragmentManager(), "dialog_query");
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


    @Override
    public void update() {
        IAddBundleFields fields = new IAddBundleFields(entity.getField(MultipleFidls.NAME),
                entity.getField(IHomeRvFields.KIND),
                entity.getField(IHomeRvFields.LONG_TIME),
                entity.getField(IHomeRvFields.CATEGORY),
                Math.abs((double)entity.getField(IHomeRvFields.CONSUME_I)),
                entity.getField(IHomeRvFields.REMARK),
                entity.getField(IHomeRvFields.KEY));
        //设置header头位置，用于更新
        presenter.setHeaderPosition(fields.getKey());
        presenter.setOndownPosition(position);
        presenter.setStateMode(IHomeStateType.UPDATE);
        diaQuery.dismiss();
        mdelegate.getSupportDelegate().start(AddDelegate.newInstance(fields));
    }

    @Override
    public void delete() {
        diaQuery.dismiss();
        diaDelete = new HomeDiaDelete(this);
        diaDelete.show(mdelegate.getChildFragmentManager(), "dialog_delete");
    }

    @Override
    public void ensure() {
        presenter.setHeaderPosition(entity.getField(IHomeRvFields.KEY));
        presenter.delegateModel(entity);
        diaDelete.dismiss();
    }

    @Override
    public void back() {
        if (diaDelete != null) {
            diaDelete.dismiss();
            diaDelete = null;
        }
    }
}
