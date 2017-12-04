package com.example.home_pc.mytestapp.MainActivityPackage;

import android.util.Log;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Model;


public class MainActivityPresenter {
    Model model;
    MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.model = new Model(this);
        this.mainActivity = mainActivity;
    }

    public BaseFragment openScreen(int id) {
        return model.getFragmentInstance(id);
    }


}
