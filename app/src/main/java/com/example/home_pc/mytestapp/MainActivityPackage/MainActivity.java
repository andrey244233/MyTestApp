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
import com.example.home_pc.mytestapp.Model;
import com.example.home_pc.mytestapp.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MUSIC_FRAGMENT = 0;
    public static final int PICTURE_FRAGMENT = 1;
    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainActivityPresenter = new MainActivityPresenter(this, new Model());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.container, new PictureFragment());
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
        Fragment showFragment = null;
        switch (item.getItemId()) {
            case R.id.nav_music:
                showFragment = openScreen(MUSIC_FRAGMENT);
                break;
            case R.id.nav_pictures:
                showFragment = openScreen(PICTURE_FRAGMENT);
                break;
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.container, showFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BaseFragment openScreen(int id) {
        return mainActivityPresenter.openScreen(id);
    }

}
