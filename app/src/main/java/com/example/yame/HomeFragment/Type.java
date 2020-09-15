package com.example.yame.HomeFragment;

import android.os.Parcel;
import android.os.Parcelable;

public class Type implements Parcelable {

    private String type;
    private int id;

    public Type(String type, int id) {
        this.type = type;
        this.id = id;
    }

    protected Type(Parcel in) {
        type = in.readString();
        id = in.readInt();
    }

    public static final Creator<Type> CREATOR = new Creator<Type>() {
        @Override
        public Type createFromParcel(Parcel in) {
            return new Type(in);
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
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
