package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.content.res.Configuration;
import android.os.Parcelable;

import com.example.home_pc.mytestapp.Picture;

import java.io.Serializable;
import java.util.ArrayList;

public interface PictureFragmentView  {

    void hideProgress();

    void getItems(ArrayList<Picture> items);

    void showProgress();

    void changeLayoutManager();

    void setGridLayoutManager();

    void setLinearLayoutManager();

    void getScreenConfiguration(Configuration configuration);

}
