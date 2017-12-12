package com.example.home_pc.mytestapp.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class InternetAccessReceiver extends BroadcastReceiver {

    public static final String CHECK_INTERNET = "com.myapp.action.INTERNET_ACCESS";
    public static Boolean accessToInternet = false;
    Model model;

    public InternetAccessReceiver() {
        model = Model.getModelInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(CHECK_INTERNET)) {
            accessToInternet = model.checkAccesToInternet(context);
        }
    }
}
