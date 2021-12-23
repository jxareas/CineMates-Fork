package com.example.cinemates.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.ActorRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaCastBinding;
import com.example.cinemates.model.CastModel;
import com.example.cinemates.model.CreditsModel;
import com.example.cinemates.model.MovieModel;
import com.example.cinemates.viewModels.CreditsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MediaCastFragment extends Fragment {

    private FragmentMediaCastBinding mBinding;
    private ActorRecyclerViewAdapter mAdapter;
    private List<CastModel> mCastModels ;


    public MediaCastFragment(CreditsModel creditsModel) {
        this.mCastModels = new ArrayList<>(creditsModel.getCast());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ActorRecyclerViewAdapter();
        mAdapter.addItems(mCastModels);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaCastBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mAdapter);


        return mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}