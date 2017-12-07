package com.example.home_pc.mytestapp.Model;

import android.app.Application;

public class CustomApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Model.initModelInstance();
    }


}
