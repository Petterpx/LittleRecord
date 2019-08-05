package com.petterp.latte_ec.presenter;

import android.os.Bundle;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.model.add.IAddBundleFields;
import com.petterp.latte_ec.model.add.IAddImpl;
import com.petterp.latte_ec.model.add.IAddModel;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.view.add.IAddView;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

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
    }

    public void showInfo(Bundle bundle) {
        model.setBundle(bundle);
        showTopVP();
        showKeyInfo();
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


}
