package com.petterp.latte_ec.main.login;

import com.petterp.latte_core.mvp.presenter.BasePresenter;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.home.MessageItems;
import com.petterp.latte_ec.main.login.imodel.ILoginModel;
import com.petterp.latte_ec.main.login.iview.ILoginView;

import org.greenrobot.eventbus.EventBus;

import cn.sharesdk.framework.Platform;

/**
 * login -> 控制层
 * @author by Petterp
 * @date 2019-07-30
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    private ILoginView iLoginView;
    private ILoginModel iLoginModel;
    @Override
    public void getView(ILoginView view) {
        iLoginView=view;
        iLoginModel=new ILoginImpl();
    }

    public void saveQQinfo(Platform platform){
        iLoginModel.qqSave(platform);
        EventBus.getDefault().post(new MessageItems(1));
        iLoginView.fragmentStartToA(R.id.homeDelegate);
    }

}
