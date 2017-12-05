package com.example.home_pc.mytestapp.MainActivityPackage;

import android.util.Log;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Model;


public class MainActivityPresenter {
    Model model;
    MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity, Model model) {
        this.mainActivity = mainActivity;
        this.model = model;
    }

    public BaseFragment openScreen(int id) {
        return model.getFragmentInstance(id);
    }


}
