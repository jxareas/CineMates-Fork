package com.example.cinemates;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemates.adapter.FragmentSearchAdapter;
import com.example.cinemates.adapter.MovieDetailActivityAdapter;
import com.example.cinemates.databinding.ActivityDetailMediaContentBinding;
import com.example.cinemates.model.MovieModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailMediaContentActivity extends AppCompatActivity {

    private ActivityDetailMediaContentBinding mBinding;
    private MovieDetailActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityDetailMediaContentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mAdapter = new MovieDetailActivityAdapter(getSupportFragmentManager(), getLifecycle());
        mBinding.viewPager.setAdapter(mAdapter);

        getDataFromIntent();

        setupTabLayout();

    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            mBinding.setMovie(movieModel);

        }

    }

    private void setupTabLayout() {
        TabLayout tabLayout = mBinding.tabLayout;
        new TabLayoutMediator(tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Info");
                        break;
                    case 1:
                        tab.setText("Cast");
                        break;
                }
            }
        }).attach();
    }

}