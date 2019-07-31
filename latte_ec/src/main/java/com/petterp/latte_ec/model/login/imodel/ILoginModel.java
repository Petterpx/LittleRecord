package com.petterp.latte_ec.model.login.imodel;

/**
 * Login -> 数据层
 *
 * @author by Petterp
 * @date 2019-07-31
 */
public interface ILoginModel {

    /**
     * 保存
     * @param t
     * @param <T>
     */
    <T> void saveData(T t);

    /**
     * 返回数据
     * @param <T>
     * @return
     */
    <T> T getData();


}
