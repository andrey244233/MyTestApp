package com.example.home_pc.mytestapp.Model;

import android.content.Context;
import com.example.home_pc.mytestapp.Api.Child;
import com.example.home_pc.mytestapp.Api.Data;
import com.example.home_pc.mytestapp.Api.Data_;
import com.example.home_pc.mytestapp.Api.RetrofitInterface;
import com.example.home_pc.mytestapp.Api.RootObject;
import com.example.home_pc.mytestapp.Picture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
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

    public ArrayList<String> getPicturesUrlsViaRetrofit(String type, final Context context, ResponseCallback responseCallback)  {
        this.callback = responseCallback;
        ArrayList<String> urls = new ArrayList<>();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitInterface client = retrofit.create(RetrofitInterface.class);
        Call<RootObject> call = client.getChilds(type, 100);
        RootObject rootObject = null;
        try {
            rootObject = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Data data = rootObject.getData();
        List<Child> children = data.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Child child = children.get(i);
            Data_ data_ = child.getData();
            String url = data_.getUrl();
            urls.add(url);
        }
        return urls;
    }

   public void useRxJava(String type, Context context, ResponseCallback responseCallback) {
        Observable observable = Observable.fromCallable(new
                                                                Callable<ArrayList<String>>() {
                                                                    @Override
                                                                    public ArrayList<String> call() throws Exception {
                                                                        return getPicturesUrlsViaRetrofit(type, context, responseCallback);
                                                                    }
                                                                });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<String>>() {
                    @Override
                    public void accept(ArrayList<String> urls) throws Exception {
                        returnUrls(urls);
                    }
                });
    }

    private void returnUrls(ArrayList<String> urls){
                        Model model = Model.getModelInstance();
                        ArrayList<Picture> pictures = new ArrayList<>();
                        pictures = model.createPictureObjectsWithUrls(urls);
                        callback.response(pictures);

    }

}



//        call.enqueue(new Callback<RootObject>() {
//            @Override
//            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
//                ArrayList<String> urls = new ArrayList<>();
//                RootObject rootObject = response.body();
//                Data data = rootObject.getData();
//                List<Child> children = data.getChildren();
//                for (int i = 0; i < children.size(); i++) {
//                    Child child = children.get(i);
//                    Data_ data_ = child.getData();
//                    String url = data_.getUrl();
//                    urls.add(url);
//
//                    if (urls.size() == children.size()) {
//                        Model model = Model.getModelInstance();
//                        ArrayList<Picture> pictures = new ArrayList<>();
//                        pictures = model.createPictureObjectsWithUrls(urls);
//                        callback.response(pictures);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RootObject> call, Throwable t) {
//                Log.v("tag", "SOME ERROR");
//                Intent intent = new Intent();
//                intent.setAction(REQUSET_FAILURE);
//                intent.putExtra(BROADCAST_KEY, ERROR_MESSAGE);
//                context.sendBroadcast(intent);
//            }
//        });
//    }
//
//
//    private ArrayList<String> sentRequest() {
//        ArrayList<String> jokes = new ArrayList<>();
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit = builder.build();
//        JokeRetrofitInterface client = retrofit.create(JokeRetrofitInterface.class);
//
//
//
//        Call<RootObject> call = client.getJokes();
//        RootObject rootObject = null;
//        try {
//            rootObject = call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Value[] values = rootObject.getValue();
//        for (Value value : values) {
//            String joke = value.getJoke();
//            jokes.add(joke);
//        }
//        return jokes;
//    }
//
//
//
//
//
//
//
//
//
//
//

//}

