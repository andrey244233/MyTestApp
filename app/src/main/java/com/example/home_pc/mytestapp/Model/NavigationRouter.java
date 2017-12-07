package com.example.home_pc.mytestapp.Model;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Fragments.MusicFragment.MusicFragment;
import com.example.home_pc.mytestapp.Fragments.MusicFragment.MusicFragmentPresenter;
import com.example.home_pc.mytestapp.Fragments.PictureFragment.PictureFragment;
import com.example.home_pc.mytestapp.Fragments.PictureFragment.PictureFragmentPresenter;
import com.example.home_pc.mytestapp.MainActivityPackage.MainActivity;

public class NavigationRouter {

    public BaseFragment getFragmentInstance(int id) {
        switch (id) {
            case MainActivity.MUSIC_FRAGMENT:
                return MusicFragment.newInstance("", "");
            case MainActivity.PICTURE_FRAGMENT:
                return PictureFragment.newInstance("", "");
        }
        return null;
    }
}
