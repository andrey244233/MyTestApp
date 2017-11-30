package com.example.home_pc.mytestapp.MainActivityPackage;

import android.util.Log;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Model;


public class MainActivityPresenter {
    Model model;

    public BaseFragment openScreen(int id) {
        model = new Model();
        return model.getFragmentInstance(id);
    }


}
