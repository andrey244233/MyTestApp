package com.example.home_pc.mytestapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.home_pc.mytestapp.Adapters.PicturesAdapter;
import com.example.home_pc.mytestapp.Fragments.BaseFragment;

import java.util.ArrayList;

public class Model {

    public void getPictureFromApi(String urlType, PicturesRetrofit.ResponseCallback responseCallback) {
        PicturesRetrofit picturesRetrofit = new PicturesRetrofit(responseCallback);
        picturesRetrofit.getPicturesUrlsViaRetrofit(urlType);
    }

    public BaseFragment getFragmentInstance(int id) {
        NavigationRouter navigationRouter = new NavigationRouter();
        return navigationRouter.getFragmentInstance(id);
    }

    public ArrayList<Picture> createPictureObjectsWithUrls(ArrayList<String> urls) {
        ArrayList<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            pictures.add(new Picture(url));
        }
        return pictures;
    }

    public boolean checkAccesToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}

