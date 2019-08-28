package com.petterp.latte_ec.main.add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.main.home.IHomeStateType;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Add-控制层
 *
 * @author by Petterp
 * @date 2019-07-24
 */
public class AddPresenter extends BasePresenter<IAddView> {
    private IAddView mView;
    private IAddModel model;

    @Override
    public void getView(IAddView view) {
        this.mView = view;
        model = new IAddImpl();
        EventBus.getDefault().register(this);
    }

    private void updateAddRv() {
        if (mView != null) {
            mView.UpdateRv();
        }
    }

    private void showTopVP() {
        if (mView != null) {
            mView.topViewPagerInfo();
        }
    }

    private void showKeyInfo() {
        if (mView != null) {
            mView.bottomKeyInfo();
            if (model.getStateMode() == IHomeStateType.UPDATE) {
                updateAddRv();
            }
        }
    }

    public List<MultipleItemEntity> getConsumeRvList() {
        return model.getConsumeRvList();
    }

    public List<MultipleItemEntity> getIncomeRvList() {
        return model.getIncomeRvList();
    }

    public List<MultipleItemEntity> getkeyRvList() {
        return model.getKeyRvList();
    }

    /**
     * 获取备注信息
     */
    public String getRemarkInfo() {
        if (mView != null) {
            return mView.getEditRemark();
        }
        return null;
    }

    /**
     * 设置当前支出还是收入
     *
     * @param mode
     */
    public void setTitleMode(String mode) {
        model.setTitleMode(mode);
    }

    /**
     * 实时更新当前键盘值
     *
     * @param res
     */
    public void setBootomKey(String res) {
        if (mView != null) {
            mView.setKeyRes(res);
        }
    }

    public String getTitleMode() {
        return model.getTitleMode();
    }

    public void setTitleRvKind(String[] kind) {
        model.setTitleRvKind(kind);
    }

    public String[] getTitleRvKind() {
        return model.getTitleRvKind();
    }
    public String[] getTitleRvKind(String mode) {
        return model.getTitleRvKind(mode);
    }

    public void setKeyRvSaveColor(boolean mode) {
        //更改颜色
        model.setKeyRvSaveColor(mode);
        if (mView != null) {
            mView.updateKeyColor(mode);
        }
    }

    public int getStateMode() {
        return model.getStateMode();
    }

    public IAddBundleFields getUpdateRvItem() {
        return model.getStateUpdate();
    }

    public void setRvItemPosition(int position) {
        model.setItemPosition(position);
    }

    @Subscribe()
    public void updateData(AddRvDataMessage addRvMessage) {
        String kind = addRvMessage.getKind();
        String kindNew = addRvMessage.getKindNew();
        String category = addRvMessage.getCategory();
        switch (addRvMessage.getMode()) {
            case ADD_ITEM_ADD:
                model.addRvItem(kindNew,category);
                break;
            case ADD_ITEM_UPDATE:
                model.updateRvItem(kind,kindNew,category);
                break;
            case ADD_ITEM_DELEGATE:
                model.delegateRvItem(kind,category);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateView(AddRvViewMessage addRvViewMessage) {
        if (mView != null) {
            switch (addRvViewMessage.getMode()) {
                case ADD_ITEM_REMOVE_VP:
                    mView.removeView();
                    break;
                case ADD_ITEM_ADD:
                    mView.addRvItem();
                    break;
                case ADD_ITEM_UPDATE:
                    mView.updateRvItem(addRvViewMessage.getPosition());
                    break;
                case ADD_ITEM_DELEGATE:
                    mView.delegateRvItem();
                    break;
            }
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void rxPostData() {
        model.queryInfo();
        if (mView != null) {
            model.setBundle(mView.getBundle());
        }
    }



    @Override
    public void rxGetData() {
        showTopVP();
        showKeyInfo();
        super.rxGetData();
    }

    @Override
    public boolean startRxMode() {
        return true;
    }
}
