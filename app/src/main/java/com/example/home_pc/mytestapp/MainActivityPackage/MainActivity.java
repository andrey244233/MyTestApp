package com.example.home_pc.mytestapp.MainActivityPackage;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Fragments.PictureFragment.PictureFragment;
import com.example.home_pc.mytestapp.R;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainActivityView {

    public static final int MUSIC_FRAGMENT = 0;
    public static final int PICTURE_FRAGMENT = 1;
    private MainActivityPresenter mainActivityPresenter;
    private Fragment showFragment;
    public static final String CURRENT_FRAGMENT = "currentFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainActivityPresenter = new MainActivityPresenter(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState != null){
            showFragment = getFragmentManager().getFragment(savedInstanceState, CURRENT_FRAGMENT);
        }else {
            showFragment = new PictureFragment();
        }
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.container, showFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_music:
                mainActivityPresenter.openScreen(MUSIC_FRAGMENT);
                break;
            case R.id.nav_pictures:
                mainActivityPresenter.openScreen(PICTURE_FRAGMENT);
                break;
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.container, showFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getFragment(BaseFragment fragment) {
       showFragment = fragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, CURRENT_FRAGMENT, showFragment);
    }

}
