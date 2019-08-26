package com.petterp.latte_ec.view.add.topViewVp.rvItems;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.model.home.MessageItems;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * AddTop Rv -> P
 *
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemPresenter extends BasePresenter<IAddTopRvItemView> {
    private IAddTopRvItemView mView;
    private IAddTopRvItemModel mModel;
    //是否刷新view
    private boolean updateViewMode=false;

    @Override
    public void getView(IAddTopRvItemView view) {
        this.mView = view;
        mModel = new IAddTopRvItemImpl();
        EventBus.getDefault().register(this);
    }

    List<MultipleItemEntity> consumeList() {
        return mModel.consumeList();
    }

    List<MultipleItemEntity> incomeList() {
        return mModel.incomeList();
    }

    @Override
    public boolean startRxMode() {
        return true;
    }

    @Override
    public void rxPostData() {
        mModel.queryInfo();
    }

    @Override
    public void rxGetData() {
        if (mView != null) {
            mView.showView();
        }
        super.rxGetData();
    }

    /**
     * 分发EvenBus
     * @param message
     */
    public void modeState(AddMessage message) {
        int mode = message.getMode();
        updateViewMode=true;
        if (mode == 0) {
            mModel.addKind(message.getKindNew(), message.getCategory());
        } else if (mode == 1) {
            mModel.updateKind(message.getKindNew(), message.getKind(), message.getCategory());
        } else {
            mModel.delegate(message.getKind(), message.getCategory());
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        //更新view
        if (updateViewMode){

        }
        super.onDestroy(owner);
    }
}
