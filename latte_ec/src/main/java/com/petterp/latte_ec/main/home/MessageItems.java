package com.petterp.latte_ec.main.home;

import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * mode=1代表update Draw操作。add=0,即默认代表add操作
 * @author by Petterp
 * @date 2019-07-24
 */
public class MessageItems {
    //所需的item
    private MultipleItemEntity itemEntity;
    //消息属性
    private int mode;
    public MessageItems(int mode) {
        this.mode=mode;
    }

    public MessageItems(MultipleItemEntity itemEntity) {
        this.itemEntity = itemEntity;
        mode=0;
    }

    public MultipleItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(MultipleItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
