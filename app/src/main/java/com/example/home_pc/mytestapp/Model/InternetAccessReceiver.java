package com.example.home_pc.mytestapp.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetAccessReceiver extends BroadcastReceiver {

    public static final String CHECK_INTERNET = "android.net.conn.CONNECTIVITY_CHANGE";
    public static Boolean accessToInternet = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(CHECK_INTERNET)) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            accessToInternet =  netInfo != null && netInfo.isConnectedOrConnecting();
            Toast.makeText(context, "Internet is = " + accessToInternet, Toast.LENGTH_LONG).show();
        }
    }
}
