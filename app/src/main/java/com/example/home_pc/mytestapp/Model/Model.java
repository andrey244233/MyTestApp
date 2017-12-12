package com.example.home_pc.mytestapp.Model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivityView;
import com.example.home_pc.mytestapp.Picture;

import java.util.ArrayList;

import static com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivityPresenter.CHECK_INTERNET_ACTION;

public class Model {

    private static Model modelInstance;

    public static Model getModelInstance(){
        if (modelInstance == null){
            return new Model();
        }
        return modelInstance;
    }

    public static void initModelInstance(){
        if(modelInstance == null){
            modelInstance = new Model();
        }
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

    public int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = Integer.parseInt(String.valueOf(size.x));
        int height = Integer.parseInt(String.valueOf(size.y));
        int screenParams[] = {width, height};
        return screenParams;
    }

    public String changeImage(int position, ArrayList<Picture> pictures) {
        Picture currentPicture = pictures.get(position);
        return currentPicture.getUrl();
    }

    public Configuration getScreenConfiguration(Context context){
        return context.getResources().getConfiguration();
    }


}

