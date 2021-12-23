package com.example.cinemates;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinemates.adapter.ViewPager2Adapter;
import com.example.cinemates.databinding.ActivityDetailMediaContentBinding;
import com.example.cinemates.fragment.MediaCastFragment;
import com.example.cinemates.fragment.MediaInfoFragment;
import com.example.cinemates.model.MovieModel;
import com.example.cinemates.viewModels.MovieListViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class DetailMediaContentActivity extends AppCompatActivity {

    private ActivityDetailMediaContentBinding mBinding;
    private ViewPager2Adapter mAdapter;
    private MovieModel mMovieModel;
    private MovieListViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityDetailMediaContentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getDataFromIntent();

        mViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        mViewModel.searchMovieById(mMovieModel.getId());

        observeAnyChange();

        setupViewPager2();

        setupTabLayout();


    }

    private void setupViewPager2() {
        mAdapter = new ViewPager2Adapter(this);
        ArrayList<Fragment> fragments = new ArrayList<>();//creates an ArrayList of Fragments
        fragments.add(new MediaInfoFragment(mMovieModel));
        fragments.add(new MediaCastFragment(mMovieModel));
        mAdapter.setData(fragments);// sets the data for the adapter
        mBinding.viewPager.setAdapter(mAdapter);
    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            mMovieModel = getIntent().getParcelableExtra("movie");
//            mBinding.setMovie(mMovieModel);

        }

    }

    private void setupTabLayout() {
        final TabLayout tabLayout = mBinding.tabLayout;
        final ArrayList<String> titles = new ArrayList<>();
        titles.add("Info");
        titles.add("Cast");
        new TabLayoutMediator(tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();
    }

    //Observing any data change
    private void observeAnyChange() {

        mViewModel.getMovieSearchedById().observe(this, new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                //observing for any data changes
                if (movieModel != null) {
                    mMovieModel = movieModel;

                    mBinding.setMovie(movieModel);
                    //Viene effettuata una seconda chiamata al metodo
                    //in quanto questo metodo è asincrono, quindi con la prima chiamata
                    //il nuovo film aggiornato non viene inviato ai fragemnt in questione
                    setupViewPager2();

                }
            }
        });

    }

}