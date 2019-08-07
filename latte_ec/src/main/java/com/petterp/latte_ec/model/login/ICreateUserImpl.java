package com.petterp.latte_ec.model.login;

import com.petterp.latte_core.util.litepal.UserInfo;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ec.model.login.imodel.ICreateUserModel;


import java.util.HashMap;

/**
 * M
 * @author by petterp
 * @date 2019-08-05
 */
public class ICreateUserImpl implements ICreateUserModel {
    @Override
    public void setSave(HashMap<Object, String> map) {
        UserInfo userInfo=new UserInfo();
        String key=map.get(MuiltFileds.KEY);
        userInfo.setAccount(map.get(MuiltFileds.USER_ACCOUNT));
        userInfo.setName(map.get(MuiltFileds.USER_NAME));
        userInfo.setPswd(map.get(MuiltFileds.USER_PSWD));
        userInfo.setSex(map.get(MuiltFileds.USER_SEX));
        userInfo.setIconUrl(map.get(MuiltFileds.USER_ICON_URL));
        userInfo.setAccountMode(map.get(MuiltFileds.USER_ACCOUNT_MODE));
        userInfo.setKey(key);
        userInfo.saveAsync().listen(success -> { });
        //保存用户信息
        LatterPreference.setUserId(key);
        //保存注册状态
        LatterPreference.setLoginMode(true);
    }
}
