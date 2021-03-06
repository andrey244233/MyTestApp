package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivity;
import com.example.home_pc.mytestapp.Model.Model;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.Model.PicturesRetrofit;

import java.util.ArrayList;

import static com.example.home_pc.mytestapp.Model.InternetAccessReceiver.CHECK_INTERNET;

public class PictureFragmentPresenter {

    private Model model;
    private PictureFragmentView pictureFragmentView;
    private PicturesRetrofit.ResponseCallback responseCallback;
    private PicturesRetrofit picturesRetrofit;

    public PictureFragmentPresenter(final PictureFragmentView pictureFragmentView) {
        this.pictureFragmentView = pictureFragmentView;
        model = Model.getModelInstance();
        picturesRetrofit = PicturesRetrofit.getPicturesRetrofitInstance();

        responseCallback = new PicturesRetrofit.ResponseCallback() {
            @Override
            public void response(ArrayList<Picture> pictures) {
                pictureFragmentView.hideProgress();
                pictureFragmentView.getItems(pictures);
            }
        };
    }

    public void getPicturesFromApi(String urlType, Context context) {
        picturesRetrofit.getPicturesUrlsViaRetrofit(urlType, context, responseCallback);
    }

    public void getPicturesForGallery(ArrayList<Picture> picturesForGallery, int position, Context context) {
        Intent intent = new Intent(context, FullScreenImageActivity.class);
        intent.putExtra(FullScreenImageActivity.PICTURES, picturesForGallery);
        intent.putExtra(FullScreenImageActivity.POSITION, position);
        context.startActivity(intent);
    }

    public void getScrenConfiguration(Context context) {
        Configuration configuration = model.getScreenConfiguration(context);
        pictureFragmentView.getScreenConfiguration(configuration);
    }

}
