package com.example.cinemates;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cinemates.adapter.ViewPager2Adapter;
import com.example.cinemates.databinding.ActivityDetailMediaContentBinding;
import com.example.cinemates.fragment.MediaCastFragment;
import com.example.cinemates.fragment.MediaInfoFragment;
import com.example.cinemates.model.MovieModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class DetailMediaContentActivity extends AppCompatActivity {

    private ActivityDetailMediaContentBinding mBinding;
    private ViewPager2Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityDetailMediaContentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getDataFromIntent();

        setupViewPager2();

        setupTabLayout();

    }

    private void setupViewPager2() {
        mAdapter = new ViewPager2Adapter(this);
        ArrayList<Fragment> fragments = new ArrayList<>();//creates an ArrayList of Fragments
        fragments.add(new MediaInfoFragment());
        fragments.add(new MediaCastFragment());
        mAdapter.setData(fragments);// sets the data for the adapter
        mBinding.viewPager.setAdapter(mAdapter);
    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            mBinding.setMovie(movieModel);

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

}