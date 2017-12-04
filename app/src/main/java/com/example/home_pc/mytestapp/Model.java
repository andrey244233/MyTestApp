package com.example.home_pc.mytestapp;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Fragments.PictureFragment.PictureFragmentPresenter;
import com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivity;
import com.example.home_pc.mytestapp.FullScreenImageActivityPackage.FullScreenImageActivityPresenter;
import com.example.home_pc.mytestapp.MainActivityPackage.MainActivityPresenter;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.net.Uri.parse;

public class Model {
    public static final int SCREEN_WIDTH = 0;
    public static final int SCREEN_HEIGHT = 1;
    private FullScreenImageActivityPresenter fullScreenImageActivityPresenter;
    private MainActivityPresenter mainActivityPresenter;
    private PictureFragmentPresenter pictureFragmentPresenter;


    public Model(FullScreenImageActivityPresenter fullScreenImageActivityPresenter) {
        this.fullScreenImageActivityPresenter = fullScreenImageActivityPresenter;
    }

    public Model(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    public Model(PictureFragmentPresenter pictureFragmentPresenter) {
        this.pictureFragmentPresenter = pictureFragmentPresenter;
    }


    public Model() {

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

    public Uri changeImage(int position, ArrayList<Picture> pictures) {
        Picture currentPicture = pictures.get(position);
        String url = currentPicture.getUrl();
        Uri uri = Uri.parse(url);
        return uri;
    }
}

