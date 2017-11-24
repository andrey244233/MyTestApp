package com.example.home_pc.mytestapp.Fragments.PictureFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.home_pc.mytestapp.Fragments.BaseFragment;
import com.example.home_pc.mytestapp.Picture;
import com.example.home_pc.mytestapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ArrayList<Picture> pictures;
    public static final String URL_TYPE_FOR_TOP_PICTURES = "top";
    public static final String URL_TYPE_FOR_NEW_PICTURES = "new";
    private ArrayList<String> urls;
    private PictureFragmentPresenter pictureFragmentPresenter;
    public Context context;

    public PictureFragment() {
        // Required empty public constructor
    }

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

        pictures = new ArrayList<>();
        urls = new ArrayList<>();
        View rootView = inflater.inflate(R.layout.fragment_picture, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view_for_pictures);
        Button btnNew = rootView.findViewById(R.id.btn_new);
        btnNew.setOnClickListener(this);
        Button btnTop = rootView.findViewById(R.id.btn_top);
        btnTop.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        String urlType;
        pictureFragmentPresenter = new PictureFragmentPresenter();
        if (!pictureFragmentPresenter.checkAccesToInternet(getActivity())) {
            Toast.makeText(getActivity(), getString(R.string.internet_not_available), Toast.LENGTH_LONG).show();
            return;
        }
        switch (view.getId()) {
            case R.id.btn_new:
                urlType = URL_TYPE_FOR_NEW_PICTURES;
                pictureFragmentPresenter.getPicturesFromApi(urlType, getActivity());
                break;
            case R.id.btn_top:
                urlType = URL_TYPE_FOR_TOP_PICTURES;
                pictureFragmentPresenter.getPicturesFromApi(urlType, getActivity());
                break;
        }
    }

    public void getPictures(ArrayList<Picture> pictures) {
        Log.v("tag", "GET PICTURES IN PICTURESFRAGMENT PICTURE SIZE = " + pictures.size());
        pictureFragmentPresenter.setAdapter(getActivity(), pictures, recyclerView);
    }

}
