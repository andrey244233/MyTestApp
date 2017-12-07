package com.example.home_pc.mytestapp.FullScreenImageActivityPackage;

import android.net.Uri;

public interface FullScreenImageActivityView {

    void getScreenSize(int[] screenParams);

    void getUri(Uri uri);

    void changeImage(int position);
}
