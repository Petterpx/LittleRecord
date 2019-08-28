package com.petterp.latte_ec.main.home;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.add.IAddBundleFields;
import com.petterp.latte_ec.main.add.IAddBundleType;
import com.petterp.latte_ec.main.home.dialog.HomeDiaDelete;
import com.petterp.latte_ec.main.home.dialog.HomeDiaQuery;
import com.petterp.latte_ec.main.home.dialog.IDialogTextListener;
import com.petterp.latte_ec.main.home.dialog.IHomeDiaQueryInfo;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;


/**
 * @author by Petterp
 * @date 2019-07-26
 */
public class HomeItemClickListener extends SimpleClickListener implements IDialogTextListener, IHomeDiaQueryInfo {
    private HomePresenter presenter;
    private MultipleItemEntity entity;
    private int position;
    private HomeDiaQuery diaQuery;
    private HomeDiaDelete diaDelete;
    private FragmentManager fragmentManager;
    private View view;

    public HomeItemClickListener(FragmentManager fragmentManager, HomePresenter presenter) {
        this.presenter = presenter;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        this.view=view;
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
            presenter.getView().fragmentStart(R.id.action_homeDelegate_to_addDelegate);
        } else {
            diaQuery = new HomeDiaQuery(entity, this);
            diaQuery.show(fragmentManager, "dialog_query");
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
                Math.abs((float)entity.getField(IHomeRvFields.CONSUME_I)),
                entity.getField(IHomeRvFields.REMARK),
                entity.getField(IHomeRvFields.KEY));
        //设置header头位置，用于更新
        presenter.setHeaderPosition(fields.getKey());
        presenter.setOndownPosition(position);
        presenter.setStateMode(IHomeStateType.UPDATE);
        diaQuery.dismiss();
        Bundle args = new Bundle();
        args.putParcelable(IAddBundleType.KEY_UPDATE_LIST, fields);
        presenter.getView().fragmentStart(R.id.action_homeDelegate_to_addDelegate,args);
    }

    @Override
    public void delete() {
        diaQuery.dismiss();
        diaDelete = new HomeDiaDelete(this);
        diaDelete.show(fragmentManager, "dialog_delete");
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
