package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cinemates.adapter.ActorRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaCastBinding;
import com.example.cinemates.model.api.CastModel;
import com.example.cinemates.model.api.CreditsModel;

import java.util.ArrayList;
import java.util.List;


public class MediaCastFragment extends Fragment {

    private FragmentMediaCastBinding mBinding;
    private ActorRecyclerViewAdapter mAdapter;
    private List<CastModel> mCastModels;


    public MediaCastFragment(List<CastModel> castModelList) {
        this.mCastModels = castModelList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ActorRecyclerViewAdapter(mCastModels);
//        mAdapter.addItems(mCastModels);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaCastBinding.inflate(inflater, container, false);
        mBinding.setItemCount(String.valueOf(mCastModels.size())+ " people");
        mBinding.recyclerView.setAdapter(mAdapter);


        return mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}