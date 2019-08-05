package com.petterp.latte_core.util.callback;


import androidx.annotation.Nullable;

/**
 * @author Petterp on 2019/6/15
 * Summary:回调
 * email：1509492795@qq.com
 */
public interface IGlobalCallback <T>{
    void executeCallback(@Nullable T args);
}
