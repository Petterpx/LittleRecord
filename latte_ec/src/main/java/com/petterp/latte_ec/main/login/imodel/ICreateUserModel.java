package com.petterp.latte_ec.main.login.imodel;

import java.util.HashMap;

/**
 * @author by petterp
 * @date 2019-08-05
 */
public interface ICreateUserModel {
    /**
     * 保存数据
     */
    void setSave(HashMap<Object,String> map);
}
