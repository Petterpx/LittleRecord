package com.petterp.latte_core.net.callBack;

/**
 * @author Petterp on 2019/4/18
 * Summary:错误接口
 * email：1509492795@qq.com
 */
public interface IError {
    void onError(int code, String msg);
}
