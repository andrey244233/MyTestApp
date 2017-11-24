package com.example.home_pc.mytestapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.home_pc.mytestapp.Adapters.PicturesAdapter;
import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Fragments.PictureFragment.PictureFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by HOME_PC on 22.11.2017.
 */

public class Model {

    private PicturesAdapter picturesAdapter;

    public void returnUrls(ArrayList<String> urls) {
        ArrayList<Picture> pictures = createPictureObjectsWithUrls(urls);
        PictureFragmentPresenter pictureFragmentPresenter = new PictureFragmentPresenter();
        pictureFragmentPresenter.returnPictures(pictures);
    }

    public void getPictureFromApi(String urlType, Context context) {
        PicturesRetrofit picturesRetrofit = new PicturesRetrofit();
        picturesRetrofit.getPicturesUrlsViaRetrofit(urlType, context);
    }

    public void setAdapter(Context context, ArrayList<Picture> pictures, RecyclerView recyclerView) {
        picturesAdapter = new PicturesAdapter(context, pictures);
        recyclerView.setAdapter(picturesAdapter);
        setRecyclerViewLayoutManager(context, recyclerView);
    }


    public BaseFragment getFragmentInstance(int id) {
        NavigationRouter navigationRouter = new NavigationRouter();
        return navigationRouter.getFragmentInstance(id);
    }


    public ArrayList<Picture> createPictureObjectsWithUrls(ArrayList<String> urls) {
        Log.v("tag", "createPictureObjectsWithUrls/ URL SIZE  == " + urls.size());
        ArrayList<String> urlsForPicture = urls;
        ArrayList<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < urlsForPicture.size(); i++) {
            String url = urlsForPicture.get(i);
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

    public void setRecyclerViewLayoutManager(Context context, RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return picturesAdapter.getItemViewType(position);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}

