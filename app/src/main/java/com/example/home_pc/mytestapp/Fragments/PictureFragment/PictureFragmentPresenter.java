package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.content.Context;
import android.content.Intent;

import com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivity;
import com.example.home_pc.mytestapp.Model.Model;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.Model.PicturesRetrofit;

import java.util.ArrayList;

public class PictureFragmentPresenter {

    Model model;
    PictureFragmentView pictureFragmentView;
    private PicturesRetrofit.ResponseCallback responseCallback;

    public PictureFragmentPresenter(final PictureFragmentView pictureFragmentView) {
        this.pictureFragmentView = pictureFragmentView;
        model = Model.getModelInstance() ;

        responseCallback = new PicturesRetrofit.ResponseCallback() {
            @Override
            public void response(ArrayList<Picture> pictures) {
                pictureFragmentView.hideProgress();
                pictureFragmentView.getItems(pictures);
            }

        };
    }

    public void getPicturesFromApi(String urlType) {
        model.getPictureFromApi(urlType, responseCallback);
    }

    public void checkAccesToInternet(Context context) {
        Boolean access = model.checkAccesToInternet(context);
        pictureFragmentView.getAccessToInternet(access);
    }

    public void getPicturesForGallery(ArrayList<Picture> picturesForGallery, int position, Context context) {
        Intent intent = new Intent(context, FullScreenImageActivity.class);
        intent.putExtra(FullScreenImageActivity.PICTURES, picturesForGallery);
        intent.putExtra(FullScreenImageActivity.POSITION, position);
        context.startActivity(intent);
    }
}
