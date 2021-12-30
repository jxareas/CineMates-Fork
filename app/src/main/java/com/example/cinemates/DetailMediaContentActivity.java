package com.example.cinemates;

import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.ActivityDetailMediaContentBinding;
import com.example.cinemates.fragment.HomeFragment;
import com.example.cinemates.fragment.MediaCastFragment;
import com.example.cinemates.fragment.MediaInfoFragment;
import com.example.cinemates.model.api.CastModel;
import com.example.cinemates.model.api.CreditsModel;
import com.example.cinemates.model.api.CrewModel;
import com.example.cinemates.model.api.MovieModel;
import com.example.cinemates.viewModels.CreditsViewModel;
import com.example.cinemates.viewModels.MovieListViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailMediaContentActivity extends AppCompatActivity {

    private static final String TAG = DetailMediaContentActivity.class.getSimpleName();
    private ActivityDetailMediaContentBinding mBinding;
    private ViewPagerAdapter mAdapter;
    private MovieModel mMovieModel;
    private MovieListViewModel mMovieViewModel;
    private CreditsViewModel mCreditsViewModel;
    private List<Fragment> mFragments;
    private List<CrewModel> mCrewModelList;
    private List<CastModel> mCastModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetailMediaContentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getDataFromIntent();

        mFragments = new ArrayList<>();//list of fragment for viewpager
        mCrewModelList = new ArrayList<>();
        mCastModelList = new ArrayList<>();
        mMovieViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        mCreditsViewModel = new ViewModelProvider(this).get(CreditsViewModel.class);

        mMovieViewModel.searchMovieById(mMovieModel.getId());
        mCreditsViewModel.searchCreditsByMovieId(mMovieModel.getId());


        observeAnyChange();

        /*mFragments.add(new MediaInfoFragment(mMovieModel, mCrewModelList));
        mFragments.add(new MediaCastFragment(mCastModelList));*/
        //Adding fragment and their titles to viewpager adapter
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, Arrays.asList("Info", "Cast"));
        mBinding.viewPager.setAdapter(mAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager, true);


    }


    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            mMovieModel = getIntent().getParcelableExtra("movie");
        }

    }


    //Observing any data change
    private void observeAnyChange() {
        mMovieViewModel.getMovieSearchedById().observe(this, new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                //observing for any data changes
                if (movieModel != null) {
                    mMovieModel = movieModel;
                    mBinding.setMovie(mMovieModel);
                }
            }
        });

        mCreditsViewModel.getCredits().observe(this, new Observer<CreditsModel>() {
            @Override
            public void onChanged(CreditsModel creditsModel) {
                if (creditsModel != null) {
                    mCastModelList.addAll(creditsModel.getCast());
                    mCrewModelList.addAll(creditsModel.getCrew());
                    mFragments.add(new MediaInfoFragment(mMovieModel, mCrewModelList));
                    mFragments.add(new MediaCastFragment(mCastModelList));
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }



}