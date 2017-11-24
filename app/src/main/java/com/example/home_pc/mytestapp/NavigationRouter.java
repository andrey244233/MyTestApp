package com.example.home_pc.mytestapp;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Fragments.MusicFragment.MusicFragmentPresenter;
import com.example.home_pc.mytestapp.Fragments.PictureFragment.PictureFragmentPresenter;

/**
 * Created by HOME_PC on 23.11.2017.
 */

public class NavigationRouter {

    public static final int MUSIC_FRAGMENT = 0;
    public static final int PICTURE_FRAGMENT = 1;

    public BaseFragment getFragmentInstance(int id) {
        switch (id) {
            case MUSIC_FRAGMENT:
                MusicFragmentPresenter musicFragmentPresenter = new MusicFragmentPresenter();
                return musicFragmentPresenter.getFragmentInstance();
            case PICTURE_FRAGMENT:
                PictureFragmentPresenter pictureFragmentPresenter = new PictureFragmentPresenter();
                return pictureFragmentPresenter.getFragmentInstance();

        }
        return null;
    }
}
