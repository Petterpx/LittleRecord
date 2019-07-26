package com.petterp.latte_ec.presenter;

import com.petterp.latte_ec.model.add.IAddImpl;
import com.petterp.latte_ec.model.add.IAddModel;
import com.petterp.latte_ec.view.add.IAddView;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;

/**
 * Add-控制层
 *
 * @author by Petterp
 * @date 2019-07-24
 */
public class AddPresenter {
    private IAddView mView;
    private IAddModel model;

    public AddPresenter(IAddView addView) {
        this.mView = addView;
        model = new IAddImpl();
    }

    public void showInfo() {
        showTopVP();
        showKeyInfo();
    }

    private void showTopVP() {
        if (mView != null) {
            mView.topViewPagerInfo();
        }
    }

    private void showKeyInfo() {
        if (mView != null) {
            mView.bottomKeyInfo();
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
}