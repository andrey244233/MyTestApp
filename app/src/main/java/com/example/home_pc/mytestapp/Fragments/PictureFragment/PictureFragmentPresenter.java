package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.content.Context;

import com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivityPresenter;
import com.example.home_pc.mytestapp.Model;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.PicturesRetrofit;

import java.util.ArrayList;

public class PictureFragmentPresenter {
   Model model;
   PictureFragment pictureFragment;

    public PictureFragmentPresenter(PictureFragment pictureFragment) {
        FullScreenImageActivityPresenter fullScreenImageActivityPresenter = new FullScreenImageActivityPresenter();
        this.pictureFragment = pictureFragment;
        model = new Model(fullScreenImageActivityPresenter);
    }

    public PictureFragmentPresenter(){
        model = new Model(this);
    }

    public void getPicturesFromApi(String urlType, PicturesRetrofit.ResponseCallback responseCallback) {
        model.getPictureFromApi(urlType, responseCallback);
    }

    public boolean checkAccesToInternet(Context context) {
        return model.checkAccesToInternet(context);
    }

    public PictureFragment getFragmentInstance() {
        return new PictureFragment();
    }

    public void getPicturesForGallery(ArrayList<Picture> picturesForGallery, int position, Context context) {
        model.getPicturesForGallery(picturesForGallery, position, context);
    }
}
