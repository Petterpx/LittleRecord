package com.petterp.latte_ec.main.analysis;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_ec.main.analysis.dia.DataClassifyRvItem;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * @author by petterp
 * @date 2019-08-13
 */
public class DataClassifyItemClickListener extends SimpleClickListener {

    private DataAnalysisPresenter presenter;
    private FragmentManager fragmentManager;

    public DataClassifyItemClickListener(DataAnalysisPresenter presenter, FragmentManager fragmentManager) {
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEntity entity= (MultipleItemEntity) adapter.getItem(position);
        new DataClassifyRvItem(presenter.classifyRvItemList(entity.getField(AnalysisFields.KIND))).show(fragmentManager,"dia");
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
}
