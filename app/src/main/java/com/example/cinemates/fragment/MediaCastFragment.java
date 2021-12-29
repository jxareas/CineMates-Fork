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

import java.util.ArrayList;
import java.util.List;


public class MediaCastFragment extends Fragment {

    private FragmentMediaCastBinding mBinding;
    private ActorRecyclerViewAdapter mAdapter;
    private List<CastModel> mCastModels ;


    public MediaCastFragment(List<CastModel> castModel) {
        this.mCastModels = new ArrayList<>(castModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ActorRecyclerViewAdapter();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaCastBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.addItems(mCastModels);


        return mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}