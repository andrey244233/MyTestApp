package com.example.home_pc.mytestapp.FullScreenImageActivityPackage;

import android.os.Parcelable;

import java.io.Serializable;

public interface FullScreenImageActivityView extends Serializable {

    void getScreenSize(int[] screenParams);

    void getUrl(String url);

    void changeImage(int position);
}
