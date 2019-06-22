package com.petterp.latte_ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.petterp.latte_ui.recyclear.BaseDataConverter;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/6/21
 * Summary:示例Demo
 * 邮箱：1509492795@qq.com
 */
public class IndexDataConvert extends BaseDataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray jsonArray= JSON.parseObject(getJsonData()).getJSONArray("data");
        int size=jsonArray.size();
        for (int i=0;i<size;i++){

        }
        return null;
    }
}
