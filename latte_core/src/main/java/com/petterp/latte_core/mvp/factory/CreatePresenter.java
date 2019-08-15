package com.petterp.latte_core.mvp.factory;


import com.petterp.latte_core.mvp.presenter.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * Presenter 注解
 *
 * @author by Petterp
 * @date 2019-08-03
 */
@Target({TYPE})
@Inherited //可重复
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface CreatePresenter {
    Class<? extends BasePresenter> value();
}
