package com.example.home_pc.mytestapp.FullScreenImageActivityPackage;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.home_pc.mytestapp.Model;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.pwittchen.swipe.library.Swipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullScreenImageActivity extends AppCompatActivity {

    @BindView(R.id.imageForPicture)
    PhotoView imageViewForPicture;
    private ArrayList<Picture> pictures = new ArrayList<>();
    private int position;
    private int screenWidth;
    private int screenHeight;
    private FullScreenImageActivityPresenter presenter;
    private Uri uri;
    private Swipe swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        ButterKnife.bind(this);

        presenter = new FullScreenImageActivityPresenter(this, new Model());

        position = getIntent().getIntExtra(FullScreenImageActivityPresenter.POSITION, 0);
        pictures = (ArrayList<Picture>) getIntent().getSerializableExtra(FullScreenImageActivityPresenter.PICTURES);
        setScreenSize();
        changeImage(position);
        swipe = new Swipe(300, 300);
        swipe.setListener(swipeListener);
    }

    mySwipeListener swipeListener = new mySwipeListener() {
        @Override
        public void onSwipedLeft(MotionEvent event) {
            super.onSwipedLeft(event);
            position += 1;
            changeImage(position);
        }

        @Override
        public void onSwipedRight(MotionEvent event) {
            super.onSwipedRight(event);
            position -= 1;
            changeImage(position);
        }
    };

    private void changeImage(int position) {
        if (position < 0) {
            this.position = pictures.size() - 1;
            position = pictures.size() - 1;
        }
        if (position > pictures.size() - 1) {
            this.position = 0;
            position = 0;
        }
        uri = presenter.changeImage(position, pictures);
        Picasso.with(this)
                .load(uri)
                .centerCrop()
                .resize(screenWidth, screenHeight)
                .into(imageViewForPicture);
    }

    private void setScreenSize() {
        int[] screenParams = presenter.getScreenSize(this);
        screenWidth = screenParams[0];
        screenHeight = screenParams[1];
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
}


