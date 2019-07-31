package com.petterp.latte_ec.presenter;

import com.petterp.latte_ec.model.login.IRegisterImpl;
import com.petterp.latte_ec.view.login.iview.IRegisterView;

/**
 * 注册-> 控制层
 *
 * @author by Petterp
 * @date 2019-07-31
 */
public class RegisterPresenter {
    private IRegisterView iView;
    private IRegisterImpl iModel;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iView = iRegisterView;
        iModel = new IRegisterImpl();
    }

    public void setCreateUser(String res) {
        iModel.createUser(res);
        showAvLoader();
    }

    public String getCode() {
        return iModel.getCode();
    }

    private void showAvLoader() {
        if (iView != null) {
            iView.showLoader();
        }
    }
}
