package com.example.home_pc.mytestapp;

import android.util.Log;

import com.example.home_pc.mytestapp.Api.Child;
import com.example.home_pc.mytestapp.Api.Data;
import com.example.home_pc.mytestapp.Api.Data_;
import com.example.home_pc.mytestapp.Api.RetrofitInterface;
import com.example.home_pc.mytestapp.Api.RootObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PicturesRetrofit {

    public static final String BASE_URL = "https://www.reddit.com/";
    private ResponseCallback callback;

    public PicturesRetrofit(ResponseCallback callback) {
        this.callback = callback;
    }

    public interface ResponseCallback {
        void response(ArrayList<Picture> pictures);
    }

    public void getPicturesUrlsViaRetrofit(String type) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitInterface client = retrofit.create(RetrofitInterface.class);

        Call<RootObject> call = client.getChilds(type, 4);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                ArrayList<String> urls = new ArrayList<>();
                RootObject rootObject = response.body();
                Data data = rootObject.getData();
                List<Child> children = data.getChildren();
                for (int i = 0; i < children.size(); i++) {
                    Child child = children.get(i);
                    Data_ data_ = child.getData();
                    String url = data_.getUrl();
                    urls.add(url);

                    if (urls.size() == children.size()) {
                        Model model = new Model();
                        ArrayList<Picture> pictures = new ArrayList<>();
                        pictures = model.createPictureObjectsWithUrls(urls);
                        callback.response(pictures);
                    }
                }
            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {
                Log.v("tag", "SOME ERROR");
            }
        });
    }
}

