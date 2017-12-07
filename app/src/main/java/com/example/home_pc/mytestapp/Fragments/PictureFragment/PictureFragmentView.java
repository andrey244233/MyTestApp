package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import com.example.home_pc.mytestapp.Picture;

import java.util.ArrayList;

public interface PictureFragmentView {

    void getAccessToInternet(Boolean access);

    void hideProgress();

    void getItems(ArrayList<Picture> items);

    void showProgress();

    void changeLayoutManager();

    void setGridLayoutManager();

    void setLinearLayoutManager();

}
