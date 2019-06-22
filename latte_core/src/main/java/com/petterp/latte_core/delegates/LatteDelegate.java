package com.petterp.latte_core.delegates;



/**
 * @author Petterp on 2019/6/20
 * Summary:
 * email：1509492795@qq.com
 */
public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate(){
        return (T)getParentFragment();
    }
}
