package com.petterp.latte_ui.recyclear;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author Petterp on 2019/4/23
 * Summary:简单Holder
 * 邮箱：1509492795@qq.com
 */
public class MultipleViewHolder extends BaseViewHolder {
    private MultipleViewHolder(View view) {
        super(view);
    }

    static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
