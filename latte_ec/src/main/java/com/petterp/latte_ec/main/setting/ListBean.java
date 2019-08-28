package com.petterp.latte_ec.main.setting;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 布局转化
 * @author by petterp
 * @date 2019-08-22
 */
public class ListBean implements MultiItemEntity {

    private int mItemtype;
    private String mText;
    private int id;
    private String icon;

    public ListBean(int mItemtype, String mText, int id,String icon) {
        this.mItemtype = mItemtype;
        this.mText = mText;
        this.id = id;
        this.icon=icon;
    }

    @Override
    public int getItemType() {
        return mItemtype;
    }

    public static final class Builder {
        private int mItemtype;
        private String mText;
        private int id;
        private String icon;
        public Builder setmItemtype(int mItemtype) {
            this.mItemtype = mItemtype;
            return this;
        }

        public Builder setmText(String mText) {
            this.mText = mText;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setIcon(String icon){
            this.icon=icon;
            return this;
        }
        public ListBean build() {
            return new ListBean(mItemtype, mText, id,icon);
        }
    }

    public int getmItemtype() {
        return mItemtype;
    }

    public String getmText() {
        return mText;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }
}
