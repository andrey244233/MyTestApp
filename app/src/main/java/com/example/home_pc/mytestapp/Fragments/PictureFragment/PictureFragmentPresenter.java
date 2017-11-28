package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.content.Context;
import android.view.View;

import com.example.home_pc.mytestapp.Model;
import com.example.home_pc.mytestapp.PicturesRetrofit;

public class PictureFragmentPresenter {
    private Model model;

    public PictureFragmentPresenter() {
        model = new Model();
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

}
