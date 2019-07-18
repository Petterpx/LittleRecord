package com.petterp.latte_ec.main.index.updateIndex;

import java.util.List;

/**
 * 实时数据查询
 * @author by Petterp
 * @date 2019-07-18
 */
public interface IqueryData<T> {
    void getListData(List<T> list);
}
