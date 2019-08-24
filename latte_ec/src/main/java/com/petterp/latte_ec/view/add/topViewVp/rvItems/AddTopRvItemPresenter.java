package com.petterp.latte_ec.view.add.topViewVp.rvItems;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;

/**
 * AddTop Rv -> P
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemPresenter extends BasePresenter<IAddTopRvItemView> {
    private IAddTopRvItemView mView;
    private IAddTopRvItemModel mModel;

    @Override
    public void getView(IAddTopRvItemView view) {
        this.mView = view;
        mModel = new IAddTopRvItemImpl();
    }

    public List<MultipleItemEntity> consumeList() {
        return mModel.consumeList();
    }

    public List<MultipleItemEntity> incomeList() {
        return mModel.incomeList();
    }
}
