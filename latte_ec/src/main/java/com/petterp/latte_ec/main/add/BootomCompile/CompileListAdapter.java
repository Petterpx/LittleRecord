package com.petterp.latte_ec.main.add.BootomCompile;

import com.petterp.latte_ui.recyclear.MultipleItemEntity;
import com.petterp.latte_ui.recyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * @author by Petterp
 * @date 2019-07-15
 */
public class CompileListAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected CompileListAdapter(List<MultipleItemEntity> data) {
        super(data);
    }
}
