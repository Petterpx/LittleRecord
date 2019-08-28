package com.petterp.latte_ec.main.add;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by Petterp
 * @date 2019-07-27
 */
public class IAddBundleFields implements Parcelable {
    private String name;
    private String kind;
    private long time;
    private String cargoy;
    private float money;
    private String remark;
    private String key;

    public IAddBundleFields(String name, String kind, long time, String cargoy, float money, String remark, String key) {
        this.name = name;
        this.kind = kind;
        this.time = time;
        this.cargoy = cargoy;
        this.money = money;
        this.remark = remark;
        this.key = key;
    }

    protected IAddBundleFields(Parcel in) {
        name = in.readString();
        kind = in.readString();
        time = in.readLong();
        cargoy = in.readString();
        money = in.readFloat();
        remark = in.readString();
        key=in.readString();
    }

    public static final Creator<IAddBundleFields> CREATOR = new Creator<IAddBundleFields>() {
        @Override
        public IAddBundleFields createFromParcel(Parcel in) {
            return new IAddBundleFields(in);
        }

        @Override
        public IAddBundleFields[] newArray(int size) {
            return new IAddBundleFields[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCargoy() {
        return cargoy;
    }

    public void setCargoy(String cargoy) {
        this.cargoy = cargoy;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(kind);
        parcel.writeLong(time);
        parcel.writeString(cargoy);
        parcel.writeFloat(money);
        parcel.writeString(remark);
        parcel.writeString(key);
    }
}
