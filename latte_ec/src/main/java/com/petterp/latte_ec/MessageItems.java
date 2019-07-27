package com.petterp.latte_ec;

import com.petterp.latte_ui.recyclear.MultipleItemEntity;

/**
 * @author by Petterp
 * @date 2019-07-24
 */
public class MessageItems {
    //所需的item
    private MultipleItemEntity itemEntity;

    public MessageItems(MultipleItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public MultipleItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(MultipleItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }
}
