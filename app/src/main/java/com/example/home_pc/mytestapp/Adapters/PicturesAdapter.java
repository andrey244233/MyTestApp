package com.example.home_pc.mytestapp.Adapters;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.R;
import java.util.ArrayList;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureViewHolder> {

    private ArrayList<Picture> listData;
    private Context context;

    public PicturesAdapter(Context context) {
        this.context = context;
    }

    public void setListData(ArrayList<Picture> pictures){
        this.listData = pictures;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("tag", "PICTURES ADAPTER CONTEXT empty = " + context.toString().isEmpty() );
        View view =  LayoutInflater.from(context).inflate(R.layout.picture_item, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PictureViewHolder holder, int position) {
        Picture currentPicture = listData.get(position);
        String url = currentPicture.getUrl();
        Log.v("tag", "url in onBynd = " + url);

        Glide.with(context)
                .load(url)
                .into(holder.pictureHolder);
    }

    @Override
    public int getItemCount() {
        if(listData == null) {
        return 0;
        }
        return listData.size();
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {
        ImageView pictureHolder;

        public PictureViewHolder(View itemView) {
            super(itemView);
            pictureHolder = itemView.findViewById(R.id.image_view_for_picture);
        }
    }

    @Override
    public int getItemViewType(int position) {
//        Log.v("tag", "POSITION = " + position);
//        if (position % 2 == 0) {
//            return 1;
//        }
//        return 2;
        return position % 2;
    }

    public void setData(ArrayList<Picture> listData){
        this.listData = listData;
        notifyDataSetChanged();
    }

}