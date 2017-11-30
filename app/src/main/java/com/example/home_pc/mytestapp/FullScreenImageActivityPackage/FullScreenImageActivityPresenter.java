package com.example.home_pc.mytestapp.FullScreenImageActivityPackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.example.home_pc.mytestapp.Model;
import com.example.home_pc.mytestapp.Picture;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class FullScreenImageActivityPresenter {
    private Model model;
    public static final String PICTURES = "pictures";
    public static final String POSITION = "position";

    public void getPicturesForGallery(ArrayList<Picture> picturesForGallery, int position, Context context) {
        Intent intent = new Intent(context, FullScreenImageActivity.class);
        intent.putExtra(PICTURES, picturesForGallery);
        intent.putExtra(POSITION, position);
        context.startActivity(intent);
    }

    public int[] getScreenSize(Context context) {
        model = new Model();
        return model.getScreenSize(context);
    }

    public Uri changeImage(int position, ArrayList<Picture> pictures){
        return model.changeImage( position, pictures);
    }
}
