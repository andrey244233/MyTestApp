package com.example.home_pc.mytestapp.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.example.home_pc.mytestapp.Model.PicturesRetrofit.BROADCAST_KEY;
import static com.example.home_pc.mytestapp.Model.PicturesRetrofit.REQUSET_FAILURE;


public class MyReceiver extends BroadcastReceiver {

    private static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
    private static final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Power Connected", Toast.LENGTH_LONG).show();
        }

        if (intent.getAction().equals(ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Power Disconnected", Toast.LENGTH_LONG).show();
        }

        if (intent.getAction().equals(REQUSET_FAILURE)) {
            String message = intent.getStringExtra(BROADCAST_KEY);
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}


