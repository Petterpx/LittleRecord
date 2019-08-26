package com.petterp.latte_ec.view.add.topViewVp.rvItems;

/**
 * @author Petterp on 2019/8/25
 * Summary:AddMessage mode=0为add,mode=1为update,mode=2为delegate
 * 邮箱：1509492795@qq.com
 */
public class AddMessage {
    private int mode;
    private String kind;
    private String category;
    private String kindNew;

    public AddMessage(int mode, String kind,String kindNew,String category) {
        this.mode = mode;
        this.kind = kind;
        this.category = category;
        this.kindNew=kindNew;
    }

    public AddMessage(int mode, String category) {
        this.mode = mode;
        this.category = category;
    }

    public String getKindNew() {
        return kindNew;
    }

    public void setKindNew(String kindNew) {
        this.kindNew = kindNew;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
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
}
