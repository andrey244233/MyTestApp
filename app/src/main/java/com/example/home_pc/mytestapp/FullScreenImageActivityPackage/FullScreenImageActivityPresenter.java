package com.example.home_pc.mytestapp.FullScreenImageActivityPackage;

import android.content.Context;

import com.example.home_pc.mytestapp.Model.Model;
import com.example.home_pc.mytestapp.Picture;

import java.util.ArrayList;

public class FullScreenImageActivityPresenter {

    Model model;
    FullScreenImageActivityView fullScreenImageActivityView;
    public static final String CHECK_INTERNET_ACTION = "com.myapp.action.CHECK_INTERNET";

    public FullScreenImageActivityPresenter(FullScreenImageActivityView fullScreenImageActivityView) {
        this.fullScreenImageActivityView = fullScreenImageActivityView;
        model = Model.getModelInstance();
    }

    public void getScreenSize(Context context) {
        int[] screenParams = model.getScreenSize(context);
        fullScreenImageActivityView.getScreenSize(screenParams);
    }

    public void changeImage(int position, ArrayList<Picture> pictures) {
        String url = model.changeImage(position, pictures);
        fullScreenImageActivityView.getUrl(url);
    }
}
