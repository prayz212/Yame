package com.example.yame.SearchFragment;

import android.os.Parcel;
import android.os.Parcelable;

public class SubItem implements Parcelable {

    private String type;
    private int id;

    public SubItem(String type, int id) {
        this.type = type;
        this.id = id;
    }

    protected SubItem(Parcel in) {
        type = in.readString();
        id = in.readInt();
    }

    public static final Creator<SubItem> CREATOR = new Creator<SubItem>() {
        @Override
        public SubItem createFromParcel(Parcel in) {
            return new SubItem(in);
        }

        @Override
        public SubItem[] newArray(int size) {
            return new SubItem[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeInt(id);
    }
}
