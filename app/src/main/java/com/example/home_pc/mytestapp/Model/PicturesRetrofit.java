package com.example.home_pc.mytestapp.Model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.home_pc.mytestapp.Api.Child;
import com.example.home_pc.mytestapp.Api.Data;
import com.example.home_pc.mytestapp.Api.Data_;
import com.example.home_pc.mytestapp.Api.RetrofitInterface;
import com.example.home_pc.mytestapp.Api.RootObject;
import com.example.home_pc.mytestapp.Model.Model;
import com.example.home_pc.mytestapp.Picture;

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
    public static final String REQUSET_FAILURE = "com.myapp.action.REQUSET_FAILURE";
    public static final String BROADCAST_KEY = "ERROR";
    private static final String ERROR_MESSAGE = "Error with network request, try later";


    private static PicturesRetrofit picturesRetrofitInstance;

      public static PicturesRetrofit getPicturesRetrofitInstance() {
        if (picturesRetrofitInstance == null) {
            return new PicturesRetrofit();
        }
        return picturesRetrofitInstance;
    }

    public static void initRetrofitInstance() {
        if (picturesRetrofitInstance == null) {
            picturesRetrofitInstance = new PicturesRetrofit();
        }
    }

    public interface ResponseCallback {
        void response(ArrayList<Picture> pictures);
    }

    public void getPicturesUrlsViaRetrofit(String type, final Context context, ResponseCallback responseCallback) {
          this.callback = responseCallback;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitInterface client = retrofit.create(RetrofitInterface.class);

        Call<RootObject> call = client.getChilds(type, 100);
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
                        Model model = Model.getModelInstance();
                        ArrayList<Picture> pictures = new ArrayList<>();
                        pictures = model.createPictureObjectsWithUrls(urls);
                        callback.response(pictures);
                    }
                }
            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {
                Log.v("tag", "SOME ERROR");
                Intent intent = new Intent();
                intent.setAction(REQUSET_FAILURE);
                intent.putExtra(BROADCAST_KEY, ERROR_MESSAGE);
                context.sendBroadcast(intent);
            }
        });
    }
}

