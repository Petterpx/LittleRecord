package com.petterp.latte_ec.main.login;

import com.petterp.latte_core.util.litepal.UserInfo;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ec.main.login.imodel.ILoginModel;


import cn.sharesdk.framework.Platform;

/**
 * @author by petterp
 * @date 2019-08-15
 */
public class ILoginImpl implements ILoginModel {

    @Override
    public void qqSave(Platform platform) {
        UserInfo userInfo=new UserInfo();
        String account=platform.getDb().getUserId();
        userInfo.setAccount(account);
        userInfo.setIconUrl(platform.getDb().getUserIcon());
//        userInfo.setSex(platform.getDb().getUserGender());
        userInfo.setName(platform.getDb().getUserName());
        userInfo.setAccountMode(String.valueOf(1));
        userInfo.save();
        //保存用户信息
        LatterPreference.setUserId(account);
        //保存注册状态
        LatterPreference.setLoginMode(true);
    }
}
