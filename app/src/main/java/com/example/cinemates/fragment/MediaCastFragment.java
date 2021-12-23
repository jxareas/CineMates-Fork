package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cinemates.databinding.FragmentMediaCastBinding;
import com.example.cinemates.databinding.FragmentMediaInfoBinding;
import com.example.cinemates.model.MovieModel;


public class MediaCastFragment extends Fragment {

    private FragmentMediaCastBinding mBinding;
    private MovieModel mMovieModel;

    public MediaCastFragment(MovieModel movieModel) {
        this.mMovieModel = movieModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaCastBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}