package com.petterp.latte_core.app;

import com.petterp.latte_core.util.storage.LatterPreference;

/**
 * @author Petterp on 2019/4/21
 * Summary:用户登录后的调用状态
 * email：1509492795@qq.com
 */
public class AccouttManager {
    private enum SignTag{
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登录后调用
     * @param state
     */
    public static void setSignState(boolean state){
        LatterPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private  static  boolean isSignIn(){
        return LatterPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public  static  void checkAccount(IUserCheker cheker){
        if (isSignIn()){
            cheker.onSignIn();
        }else{
            cheker.onNotSoignIn();
        }
    }
}
