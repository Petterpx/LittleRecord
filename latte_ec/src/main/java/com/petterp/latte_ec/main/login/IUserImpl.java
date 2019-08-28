package com.petterp.latte_ec.main.login;

import com.petterp.latte_core.util.litepal.UserInfo;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ec.main.login.imodel.IUserModel;

import org.litepal.LitePal;

import java.util.HashMap;
import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-06
 */
public class IUserImpl implements IUserModel {

    private HashMap<Object, String> map;

    @Override
    public void updateData(Object key, String value) {
        map.put(key, value);
    }

    @Override
    public HashMap<Object, String> queryData() {
        List<UserInfo> userId = LitePal.where("account=?", LatterPreference.getUserId()).limit(1).find(UserInfo.class);
        map = new HashMap<>();
        map.put(MuiltFileds.USER_ICON_URL, userId.get(0).getIconUrl());
        map.put(MuiltFileds.USER_NAME, userId.get(0).getName());
        map.put(MuiltFileds.USER_SEX, userId.get(0).getSex());
        map.put(MuiltFileds.USER_ACCOUNT_MODE, userId.get(0).getAccountMode());
        map.put(MuiltFileds.ID, String.valueOf(userId.get(0).getId()));
        return map;
    }

    @Override
    public void saveData() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(map.get(MuiltFileds.USER_NAME));
        userInfo.setSex(map.get(MuiltFileds.USER_SEX));
        userInfo.setIconUrl(map.get(MuiltFileds.USER_ICON_URL));
        userInfo.updateAsync(Long.parseLong(map.get(MuiltFileds.ID))).listen(rowsAffected -> { });
    }


}
