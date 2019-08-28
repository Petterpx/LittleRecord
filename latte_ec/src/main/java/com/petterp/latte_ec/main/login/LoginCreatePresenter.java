package com.petterp.latte_ec.main.login;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.main.login.imodel.ICreateUserModel;
import com.petterp.latte_ec.main.login.iview.ICreateUserView;

import java.util.HashMap;

/**
 * 注册具体信息 P
 * @author by petterp
 * @date 2019-08-05
 */
public class LoginCreatePresenter extends BasePresenter<ICreateUserView> {

    private ICreateUserModel iModel;
    @Override
    public void getView(ICreateUserView view) {
        iModel=new ICreateUserImpl();
    }

    public void setSave(HashMap<Object,String> map){
        iModel.setSave(map);
    }

}
