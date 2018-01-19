package com.example.home_pc.mytestapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Picture implements Serializable, Parcelable{

   private String url;

    public Picture()  {
    }

    public Picture(String url) {
        this.url = url;
    }

    protected Picture(Parcel in) {
        url = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
    }
}
