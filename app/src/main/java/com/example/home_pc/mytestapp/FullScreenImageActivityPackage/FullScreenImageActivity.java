package com.example.home_pc.mytestapp.FullScreenImageActivityPackage;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullScreenImageActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.imageForPicture)
    PhotoView imageViewForPicture;
    @BindView(R.id.btn_before)
    ImageButton btnBefore;
    @BindView(R.id.btn_next)
    ImageButton btnNext;
    private ArrayList<Picture> pictures = new ArrayList<>();
    private int position;
    private int screenWidth;
    private int screenHeight;
    private FullScreenImageActivityPresenter presenter;
    private int[] screenParams = new int[2];
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        ButterKnife.bind(this);
        btnBefore.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        position = getIntent().getIntExtra(FullScreenImageActivityPresenter.POSITION, 0);
        pictures = (ArrayList<Picture>) getIntent().getSerializableExtra(FullScreenImageActivityPresenter.PICTURES);
        setScreenSize();
        changeImage(position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_before:
                position -= 1;
                changeImage(position);
                break;
            case R.id.btn_next:
                position += 1;
                changeImage(position);
                break;
        }
    }

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
        presenter = new FullScreenImageActivityPresenter();
        int[] screenParams = presenter.getScreenSize(this);
        screenWidth = screenParams[0];
        screenHeight = screenParams[1];
    }
}


