package com.petterp.latte_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte_core.app.AccouttManager;

/**
 * @author Petterp on 2019/4/21
 * Summary:帮助类
 * email：1509492795@qq.com
 */
public class SignHandler {
    public static void onSignUP(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response);

        final String name = profileJson.getString("name");
        final String email = profileJson.getString("email");
        final String phone = profileJson.getString("phone");
        final String pswd = profileJson.getString("pswd");
//
//        final UserProfile profile = new UserProfile();
//        profile.setEmail(email);
//        profile.setName(name);
//        profile.setPhone(phone);
//        profile.setPswd(pswd);
//        //数据插入到数据库里
//        DatabaseManager.getInstance().getDao().insert(profile);

        //保存用户状态，已经登录成功了
        AccouttManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

    public static boolean onSignIN(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response);
        boolean mode = true;
        final String email = profileJson.getString("email");
        final String pswd = profileJson.getString("pswd");

//        //获取QueryBuilder
//        QueryBuilder qb = DatabaseManager.getInstance().getDao().queryBuilder();
//        //根据条件查询
//        List list=qb.where(UserProfileDao.Properties.Email.eq(email))
//                .where(UserProfileDao.Properties.Pswd.eq(pswd))     //设置查询条件
//                .list();
//        if (list.size()>0){
//            AccouttManager.setSignState(true);
//            signListener.onSignInSuccess();
//        } else {
//            mode = false;
//        }
        return mode;
    }
}
