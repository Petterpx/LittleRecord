package com.petterp.latte_ec.main.analysis;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_ec.main.analysis.dia.DataBillRvItem;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * @author by petterp
 * @date 2019-08-14
 */
public class DataBillItemClickListener extends SimpleClickListener {

    private DataAnalysisPresenter presenter;
    private FragmentManager fragmentManager;

    public DataBillItemClickListener(DataAnalysisPresenter presenter, FragmentManager fragmentManager) {
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEntity entity= (MultipleItemEntity) adapter.getItem(position);
        new DataBillRvItem(presenter.billRvItemList(entity.getField(AnalysisFields.YEAR_MONTH_DAY)))
                .show(fragmentManager,"billDia");
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
