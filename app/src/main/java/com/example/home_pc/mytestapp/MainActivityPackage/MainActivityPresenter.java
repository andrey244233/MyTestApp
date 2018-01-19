package com.example.home_pc.mytestapp.MainActivityPackage;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Model.Model;

public class MainActivityPresenter {
    Model model;
    MainActivityView mainActivityView;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        model = Model.getModelInstance();
    }

    public void openScreen(int id) {
        BaseFragment fragment = model.getFragmentInstance(id);
        mainActivityView.getFragment(fragment);
    }
}
