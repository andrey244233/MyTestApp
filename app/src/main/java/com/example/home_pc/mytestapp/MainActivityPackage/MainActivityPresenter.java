package com.example.home_pc.mytestapp.MainActivityPackage;

import android.util.Log;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Model;

/**
 * Created by HOME_PC on 21.11.2017.
 */

public class MainActivityPresenter {
    Model model;

    public BaseFragment openScreen(int id) {
        model = new Model();
        return model.getFragmentInstance(id);
    }


}
