package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.adapter.SectionRecyclerViewAdapter;
import com.example.cinemates.databinding.FragmentHomeBinding;
import com.example.cinemates.model.api.MovieModel;
import com.example.cinemates.model.Section;
import com.example.cinemates.viewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding mBinding;
    private NavController mNavController;
    private final List<Section> mSectionList = new ArrayList<>();
    private SectionRecyclerViewAdapter mSectionRecyclerViewAdapter;
    private MovieListViewModel mViewModel;
    private static final String TAG = HomeFragment.class.getSimpleName();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSectionRecyclerViewAdapter = new SectionRecyclerViewAdapter();
        mViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        //Getting Popular
        mViewModel.searchMoviePop(1);

        initDataIntoMovieSections();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mBinding.recyclerView.setAdapter(mSectionRecyclerViewAdapter);


        // RecyclerView Pagination
        // Loading next page of api response
        mBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (recyclerView.canScrollVertically(1)) {
                    //here we need to display  the next search result in the next page of api
                    mViewModel.searchNextPage();
                }
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        setupAppBar(view);

        mBinding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_accountFragment);
            }
        });
    }


    private void setupAppBar(@NonNull View view) {
        mNavController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment).build();
        Toolbar toolbar = mBinding.toolbar;

        NavigationUI.setupWithNavController(toolbar, mNavController, appBarConfiguration);

    }


    private void initDataIntoMovieSections() {
        //Title of sections
        final String sectionOneName = "Popolari";
        final String sectionTwoName = "Al Cinema";
        final String sectionThreeName = "In Arrivo";

        //Section list of Items
        List<MovieModel> sectionOneItems = new ArrayList<>();
        List<MovieModel> sectionTwoItems = new ArrayList<>();
        List<MovieModel> sectionThreeItems = new ArrayList<>();

        observePopularMovies(sectionOneItems);

        //Create sections
        mSectionList.add(new Section(sectionOneName, sectionOneItems));
        mSectionList.add(new Section(sectionTwoName, sectionTwoItems));
        mSectionList.add(new Section(sectionThreeName, sectionThreeItems));

        //Add section list into recycler view
        mSectionRecyclerViewAdapter.addItems(mSectionList);


    }

    private void observePopularMovies(List<MovieModel> popularMovies) {

        mViewModel.getPop().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //observing for any data change
                if (movieModels != null) {
                    popularMovies.addAll(movieModels);
                }

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}