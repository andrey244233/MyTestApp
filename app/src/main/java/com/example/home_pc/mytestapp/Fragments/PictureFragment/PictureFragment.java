package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.home_pc.mytestapp.Adapters.PicturesAdapter;
import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.MainActivityPackage.MainActivity;
import com.example.home_pc.mytestapp.Model.InternetAccessReceiver;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.R;

import java.util.ArrayList;

import static com.example.home_pc.mytestapp.Model.InternetAccessReceiver.CHECK_INTERNET;
import static com.example.home_pc.mytestapp.Model.InternetAccessReceiver.accessToInternet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureFragment extends BaseFragment implements View.OnClickListener, PicturesAdapter.ItemClickCallback, PictureFragmentView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public static final String URL_TYPE_FOR_TOP_PICTURES = "top";
    public static final String URL_TYPE_FOR_NEW_PICTURES = "new";
    private RecyclerView recyclerView;
    private PictureFragmentPresenter pictureFragmentPresenter;
    private PicturesAdapter picturesAdapter;
    public ArrayList<Picture> picturesForGallery = new ArrayList<>();
    private ProgressDialog progressDialog;
    private String urlType = URL_TYPE_FOR_TOP_PICTURES;
    public static final String PICTURES = "pictures";
    private InternetAccessReceiver internetAccessReceiver;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PictureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PictureFragment newInstance(String param1, String param2) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_picture, container, false);
        internetAccessReceiver = new InternetAccessReceiver();
        Button btnNew = rootView.findViewById(R.id.btn_new);
        btnNew.setOnClickListener(this);
        Button btnTop = rootView.findViewById(R.id.btn_top);
        btnTop.setOnClickListener(this);
        Button btnChangeLayout = rootView.findViewById(R.id.btn_change_layout);
        btnChangeLayout.setOnClickListener(this);
        recyclerView = rootView.findViewById(R.id.recycler_view_for_pictures);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(100);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        picturesAdapter = new PicturesAdapter(getActivity());
        recyclerView.setAdapter(picturesAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pictureFragmentPresenter = new PictureFragmentPresenter(this);
        picturesAdapter.setItemClickCallback(this);

        pictureFragmentPresenter.getScrenConfiguration(getActivity());

        if (accessToInternet & savedInstanceState == null) {
            showProgress();
            pictureFragmentPresenter.getPicturesFromApi(urlType, getActivity());
            picturesAdapter.setData(picturesForGallery);
        } else if (savedInstanceState != null) {
            picturesForGallery = savedInstanceState.getParcelableArrayList(PICTURES);
            picturesAdapter.setData(picturesForGallery);
        }
    }

    @Override
    public void onClick(View view) {
        if (!accessToInternet) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_new:
                showProgress();
                urlType = URL_TYPE_FOR_NEW_PICTURES;
                pictureFragmentPresenter.getPicturesFromApi(urlType, getActivity());
                break;
            case R.id.btn_top:
                showProgress();
                urlType = URL_TYPE_FOR_TOP_PICTURES;
                pictureFragmentPresenter.getPicturesFromApi(urlType, getActivity());
                break;
            case R.id.btn_change_layout:
                changeLayoutManager();
                break;
        }
        if (progressDialog != null) {
            hideProgress();
        }
    }

    private void checkInternet() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(accessToInternet){
                    Toast.makeText(getActivity(), "Подключение к интернету установлено", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Подключите интернет и нажмите кнопку", Toast.LENGTH_SHORT).show();
                }

            }
        }, 10000);
    }

    @Override
    public void onItemClick(int position) {
        pictureFragmentPresenter.getPicturesForGallery(picturesForGallery, position, getActivity());
    }

    @Override
    public void changeLayoutManager() {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            setLinearLayoutManager();
        } else {
            setGridLayoutManager();
        }
    }

    @Override
    public void setGridLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 0) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void setLinearLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.cancel();
    }

    @Override
    public void getItems(ArrayList<Picture> items) {
        picturesAdapter.setData(items);
        picturesForGallery = items;
    }

    @Override
    public void getScreenConfiguration(Configuration configuration) {
        onConfigurationChanged(configuration);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setGridLayoutManager();
        } else {
            setLinearLayoutManager();
        }
        super.onConfigurationChanged(configuration);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(PICTURES, picturesForGallery);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(CHECK_INTERNET);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(internetAccessReceiver, intentFilter);
        checkInternet();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(internetAccessReceiver);
    }

}