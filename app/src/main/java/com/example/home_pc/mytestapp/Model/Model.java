package com.example.home_pc.mytestapp.Model;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;
import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Picture;

import java.util.ArrayList;

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

    public Uri changeImage(int position, ArrayList<Picture> pictures) {
        Picture currentPicture = pictures.get(position);
        String url = currentPicture.getUrl();
        Uri uri = Uri.parse(url);
        return uri;
    }
}

