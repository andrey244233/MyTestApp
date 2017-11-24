package com.example.home_pc.mytestapp.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetrofitInterface {
    @GET("/r/EarthPorn/{type}/.json")
    Call<RootObject> getChilds(@Path("type") String type, @Query("limit") int limitQuantity);
}
