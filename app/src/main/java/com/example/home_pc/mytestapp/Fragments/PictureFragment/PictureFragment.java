package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.R;
import java.util.ArrayList;

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
    private  Boolean access;

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
        Button btnNew = rootView.findViewById(R.id.btn_new);
        btnNew.setOnClickListener(this);
        Button btnTop = rootView.findViewById(R.id.btn_top);
        btnTop.setOnClickListener(this);
        Button btnChangeLayout = rootView.findViewById(R.id.btn_change_layout);
        btnChangeLayout.setOnClickListener(this);
        recyclerView = rootView.findViewById(R.id.recycler_view_for_pictures);
        recyclerView.setHasFixedSize(true);
        setGridLayoutManager();
        picturesAdapter = new PicturesAdapter(getActivity());
        recyclerView.setAdapter(picturesAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pictureFragmentPresenter = new PictureFragmentPresenter(this);
        picturesAdapter.setItemClickCallback(this);
    }

    @Override
    public void onClick(View view) {
        String urlType;
        pictureFragmentPresenter.checkAccesToInternet(getActivity());
        if (!access) {
            Toast.makeText(getActivity(), getString(R.string.internet_not_available), Toast.LENGTH_LONG).show();
            return;
        }
        switch (view.getId()) {
            case R.id.btn_new:
                showProgress();
                urlType = URL_TYPE_FOR_NEW_PICTURES;
                pictureFragmentPresenter.getPicturesFromApi(urlType);
                break;
            case R.id.btn_top:
                showProgress();
                urlType = URL_TYPE_FOR_TOP_PICTURES;
                pictureFragmentPresenter.getPicturesFromApi(urlType);
                break;
            case R.id.btn_change_layout:
                changeLayoutManager();
                break;
        }
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
    public void getAccessToInternet(Boolean access) {
        this.access = access;
    }

}