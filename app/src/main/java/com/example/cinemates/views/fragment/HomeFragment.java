package com.example.cinemates.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cinemates.R;
import com.example.cinemates.adapter.MovieRecyclerViewAdapter;
import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentHomeBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Section;
import com.example.cinemates.util.Constants;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.example.cinemates.viewmodel.MovieViewModel;
import com.example.cinemates.views.MovieDetailsActivity;
import com.example.cinemates.views.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private Section<Movie> upcomingSection, topRatedSection, trendingSection;
    private Toolbar mToolbar;
    private MovieViewModel mViewModel;
    private SectionRecyclerViewAdapter<Movie> mAdapter;
    private List<Section<Movie>> mSectionList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SectionRecyclerViewAdapter<>(this);
        upcomingSection = new Section<>("Upcoming", null);
        topRatedSection = new Section<>("Top Rated", null);
        trendingSection = new Section<>("Trending", null);
        mSectionList = new ArrayList<>();
        mSectionList.add(upcomingSection);
        mSectionList.add(topRatedSection);
        mSectionList.add(trendingSection);

        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);


        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(mNavController.getGraph()).build();
        mToolbar = mBinding.toolbar;
        NavigationUI.setupWithNavController(mToolbar, mNavController, appBarConfiguration);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        mBinding.sectionRv.setAdapter(mAdapter);
        mAdapter.addItems(mSectionList);

        observeData();
        mViewModel.getCurrentlyShowingMovies();
        mViewModel.getUpcomingMovies();
        mViewModel.getTopRatedMovies();
        mViewModel.getPopularMovies();
        mViewModel.getTrendingMovies(MediaType.MOVIE, TimeWindow.WEEK);


        mBinding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open drawer
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_accountFragment);
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.discoverFragment) {
                    Intent intent = new Intent(view.getContext(), SearchActivity.class);
                    view.getContext().startActivity(intent);
                }
                return false;
            }
        });
    }

    private void observeData() {
        upcomingSection.setMutableLiveData(mViewModel.getUpcomingMoviesList());
        topRatedSection.setMutableLiveData(mViewModel.getTopRatedMoviesList());
        trendingSection.setMutableLiveData(mViewModel.getTrendingMovieList());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}