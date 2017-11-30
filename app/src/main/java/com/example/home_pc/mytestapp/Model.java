package com.example.home_pc.mytestapp;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.view.WindowManager;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivityPresenter;

import java.util.ArrayList;

public class Model {
    FullScreenImageActivityPresenter fullScreenImageActivityPresenter;

    public Model() {
        fullScreenImageActivityPresenter = new FullScreenImageActivityPresenter();
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

    public void getPicturesForGallery(ArrayList<Picture> picturesForGallery, int position, Context context) {
        fullScreenImageActivityPresenter.getPicturesForGallery(picturesForGallery, position, context);
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
}

