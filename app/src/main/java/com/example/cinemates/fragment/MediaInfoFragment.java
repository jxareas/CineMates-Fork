package com.example.cinemates.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.GenreRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaInfoBinding;
import com.example.cinemates.model.GenreModel;
import com.example.cinemates.model.MovieModel;
import com.example.cinemates.viewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;


public class MediaInfoFragment extends Fragment {

    private FragmentMediaInfoBinding mBinding;
    private MovieModel mMovieModel;
    private GenreRecyclerViewAdapter mGenreAdapter;


    public MediaInfoFragment(MovieModel movieModel) {
        this.mMovieModel = movieModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenreAdapter = new GenreRecyclerViewAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaInfoBinding.inflate(inflater, container, false);
        mBinding.setMovie(mMovieModel);

        mGenreAdapter.addItems(mMovieModel.getGenres());
        //set adapter to recycler view
        mBinding.recyclerViewGenres.setAdapter(mGenreAdapter);

        return mBinding.getRoot();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;

    }
}