package com.example.home_pc.mytestapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureViewHolder> {

    private ArrayList<Picture> listData;
    private Context context;
    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int position);
    }

    public void setItemClickCallback(ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public PicturesAdapter(Context context) {
        this.context = context;
    }

    public void setListData(ArrayList<Picture> pictures) {
        this.listData = pictures;
    }

    public Picture getItemByPosition(int position) {
        Picture picture = new Picture();
        picture = listData.get(position);
        return picture;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.picture_item, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PictureViewHolder holder, int position) {
        Picture currentPicture = listData.get(position);
        String url = currentPicture.getUrl();

        // Uri uri = Uri.parse(url);
//        Picasso.with(context)
//                .load(uri)
//                .noFade()
//                .resize(300, 200)
//                .into(holder.pictureHolder);
        Glide.with(context)
                .load(url)
                .into(holder.pictureHolder);
    }

    @Override
    public int getItemCount() {
        if (listData == null) {
            return 0;
        }
        return listData.size();
    }

    public void setData(ArrayList<Picture> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pictureHolder;

        public PictureViewHolder(View itemView) {
            super(itemView);
            pictureHolder = itemView.findViewById(R.id.image_view_for_picture);
            pictureHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.image_view_for_picture) {
                itemClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }


}