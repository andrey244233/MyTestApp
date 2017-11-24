package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.home_pc.mytestapp.Model;
import com.example.home_pc.mytestapp.Picture;

import java.util.ArrayList;

/**
 * Created by HOME_PC on 22.11.2017.
 */

public class PictureFragmentPresenter {

    private Model model = new Model();

    public void setAdapter(Context context, ArrayList<Picture> pictures, RecyclerView recyclerView) {
        model.setAdapter(context, pictures, recyclerView);
        Log.v("tag", "CONTEX =" + context);
    }

    public void getPicturesFromApi(String urlType, Context context) {
        model.getPictureFromApi(urlType, context);
    }

    public boolean checkAccesToInternet(Context context) {
        return model.checkAccesToInternet(context);
    }

    public PictureFragment getFragmentInstance() {
        return new PictureFragment();
    }

    public void returnPictures(ArrayList<Picture> pictures) {
        PictureFragment fragment = getFragmentInstance();
        fragment.getPictures(pictures);
    }
}
