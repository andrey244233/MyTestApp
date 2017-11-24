package com.example.home_pc.mytestapp;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.home_pc.mytestapp.Api.Child;
import com.example.home_pc.mytestapp.Api.Data;
import com.example.home_pc.mytestapp.Api.Data_;
import com.example.home_pc.mytestapp.Api.Example;
import com.example.home_pc.mytestapp.Api.RetrofitInterface;
import com.example.home_pc.mytestapp.Api.RootObject;
import com.example.home_pc.mytestapp.Fragments.PictureFragment.PictureFragmentPresenter;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HOME_PC on 23.11.2017.
 */

public class PicturesRetrofit  {

    public static final String BASE_URL = "https://www.reddit.com/";
    RootObject rootObject;
    ArrayList<String> urls = new ArrayList<>();


    public void getPicturesUrlsViaRetrofit(String type, final Context context) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitInterface client = retrofit.create(RetrofitInterface.class);

        Call<RootObject> call = client.getChilds(type, 2);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                rootObject = response.body();
                Data data = rootObject.getData();
                List<Child> children = data.getChildren();
                for (int i = 0; i < children.size(); i++) {
                    Child child = children.get(i);
                    Data_ data_ = child.getData();
                    String url = data_.getUrl();
                    urls.add(url);
                    if(urls.size() == children.size()){
                        Model model = new Model();
                        model.returnUrls(urls);
                    }
                }
            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {

            }
        });

    }
}

