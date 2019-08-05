package com.petterp.latte_ec.model.login;

import com.petterp.latte_core.util.litepal.UserInfo;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ec.model.login.imodel.ICreateUserModel;

import org.litepal.crud.callback.SaveCallback;

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
        String id=map.get(MuiltFileds.KEY);
        userInfo.setPhone(map.get(MuiltFileds.USER_PHONE));
        userInfo.setName(map.get(MuiltFileds.USER_NAME));
        userInfo.setPswd(map.get(MuiltFileds.USER_PSWD));
        userInfo.setSex(map.get(MuiltFileds.USER_SEX));
        userInfo.setIconUrl(map.get(MuiltFileds.USER_ICON_URL));
        userInfo.setKey(id);
        userInfo.saveAsync().listen(success -> { });
        //保存用户信息
        LatterPreference.setUserId("userId",id);
        //保存注册状态
        LatterPreference.setLoginMode("login",true);
    }
}
