package com.petterp.latte_core.mvp.view;


import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;

/**
 * @author by Petterp
 * @date 2019-08-03
 */
public interface IBaseView {
    /**
     * 销毁
     */
    void onDetachView();

    /**
     * 显示Loader
     */
    void showLoader();

    /**
     * 停止Loader
     */
    void stopLoader();

    /**
     * fragment基本跳转
     * A->B
     *
     * @param id
     */
    void fragmentStart(@IdRes int id);

    /**
     * fragment 携带数据跳转-Bundle
     * A->B
     *
     * @param id
     * @param bundle
     */
    void fragmentStart(@IdRes int id, @Nullable Bundle bundle);

    /**
     * fragment 携带数据新写法，Navigation 标准写法
     * 需要在 nav_host中添加相应的argument，自动生成 以下类
     * 传递端 ClassName+Directions ，接收端ClassName+Args
     * <p>
     * Demo: (传递端)RegisterDelegateDirections.actionRegisterDelegateToCreateUserDelegate(phone)
     * (接收端)CreateUserDelegateArgs.fromBundle(getArguments()).getPhone()
     *
     * @param directions
     */
    void fragmentStart(@NonNull NavDirections directions);

    /**
     * 退栈方法
     */
    void fragmentUP();

    /**
     * 跳转方法，多级跳转
     * A->B->C,C->A,避免Navigation 跳转后重新执行生命周期方法
     *
     * @param id
     * @return 如果退栈一次就返回true
     */
    boolean fragmentStartToA(@IdRes int id);
}
