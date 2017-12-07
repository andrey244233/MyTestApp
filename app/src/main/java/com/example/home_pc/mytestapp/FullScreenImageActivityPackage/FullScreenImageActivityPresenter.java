package com.example.home_pc.mytestapp.FullScreenImageActivityPackage;

import android.content.Context;
import android.net.Uri;

import com.example.home_pc.mytestapp.Model.Model;
import com.example.home_pc.mytestapp.Picture;

import java.util.ArrayList;

public class FullScreenImageActivityPresenter {

    Model model;
    FullScreenImageActivityView fullScreenImageActivityView;

    public FullScreenImageActivityPresenter(FullScreenImageActivityView fullScreenImageActivityView) {
        this.fullScreenImageActivityView = fullScreenImageActivityView;
        model = Model.getModelInstance();
    }

    public void getScreenSize(Context context) {
        int[] screenParams = model.getScreenSize(context);
        fullScreenImageActivityView.getScreenSize(screenParams);
    }

    public void changeImage(int position, ArrayList<Picture> pictures) {
        Uri uri = model.changeImage(position, pictures);
        fullScreenImageActivityView.getUri(uri);
    }
}
