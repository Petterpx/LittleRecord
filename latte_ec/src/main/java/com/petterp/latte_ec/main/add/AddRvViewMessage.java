package com.petterp.latte_ec.main.add;

/**
 * @author Petterp on 2019/8/26
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class AddRvViewMessage {
    private AddItemFileds mode;
    private int position;

    public AddRvViewMessage(AddItemFileds mode) {
        this.mode = mode;
    }

    public AddRvViewMessage(AddItemFileds mode, int position) {
        this.mode = mode;
        this.position = position;
    }

    public AddItemFileds getMode() {
        return mode;
    }

    public int getPosition() {
        return position;
    }
}
