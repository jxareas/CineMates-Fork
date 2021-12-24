package com.example.cinemates.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.CrewRecyclerViewAdapter;
import com.example.cinemates.adapter.GenreRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentMediaInfoBinding;
import com.example.cinemates.databinding.LayoutBackdropCollectionBinding;
import com.example.cinemates.model.CrewModel;
import com.example.cinemates.model.GenreModel;
import com.example.cinemates.model.MovieModel;
import com.example.cinemates.viewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;


public class MediaInfoFragment extends Fragment {

    private FragmentMediaInfoBinding mBinding;
    private MovieModel mMovieModel;
    private List<CrewModel> mCrewModel;
    private GenreRecyclerViewAdapter mGenreAdapter;
    private CrewRecyclerViewAdapter mCrewAdapter;


    public MediaInfoFragment(MovieModel movieModel, List<CrewModel> crew) {
        this.mMovieModel = movieModel;
        this.mCrewModel = new ArrayList<>(crew);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenreAdapter = new GenreRecyclerViewAdapter();
        mCrewAdapter = new CrewRecyclerViewAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMediaInfoBinding.inflate(inflater, container, false);
        mBinding.setMovie(mMovieModel);

        mGenreAdapter.addItems(mMovieModel.getGenres());
        mCrewAdapter.addItems(mCrewModel);
        //set adapter to recycler view
        mBinding.recyclerViewGenres.setAdapter(mGenreAdapter);
        mBinding.recyclerViewCrew.setAdapter(mCrewAdapter);



        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.backdropCollection.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO navigate to fragment for show movies in this collection
                Toast.makeText(view.getContext(), "Collection: "+mMovieModel.getBelongs_to_collection().getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;

    }
}