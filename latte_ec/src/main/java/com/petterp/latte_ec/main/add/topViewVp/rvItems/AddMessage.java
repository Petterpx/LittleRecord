package com.petterp.latte_ec.main.add.topViewVp.rvItems;

import com.petterp.latte_ec.main.add.AddItemFileds;

/**
 * @author Petterp on 2019/8/25
 * Summary:AddMessage mode=0为add,mode=1为update,mode=2为delegate
 * 邮箱：1509492795@qq.com
 */
public class AddMessage {
    private AddItemFileds mode;
    private String kind;
    private String category;
    private String kindNew;
    private int position;

    public AddMessage(AddItemFileds mode, String kind,String kindNew,String category,int position) {
        this.mode = mode;
        this.kind = kind;
        this.category = category;
        this.kindNew=kindNew;
        this.position=position;
    }

    public AddMessage(AddItemFileds mode, String category) {
        this.mode = mode;
        this.category = category;
    }

    public AddMessage(AddItemFileds mode, String kind, String category) {
        this.mode = mode;
        this.kind = kind;
        this.kindNew=kind;
        this.category = category;
    }




    public String getKindNew() {
        return kindNew;
    }

    public void setKindNew(String kindNew) {
        this.kindNew = kindNew;
    }

    public AddItemFileds getMode() {
        return mode;
    }

    public void setMode(AddItemFileds mode) {
        this.mode = mode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPosition() {
        return position;
    }
}
