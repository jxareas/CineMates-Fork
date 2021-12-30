package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.Arrays;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding mBinding;
    private ViewPagerAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private boolean layoutGrid = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mGridLayoutManager = new GridLayoutManager(getContext(), 3);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSearchBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupAppBar(view);
        setupViewPager();
//        setupTabLayout();

        // Listen menu item click and change layout into recyclerview
        // owned by SearchActor & SearchMovie fragment
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_switch_grid:
                        switchLayout(mGridLayoutManager);
                        break;
                    case R.id.menu_switch_list:
                        switchLayout(mLinearLayoutManager);
                        break;
                }
                updateToolbar();
                return false;
            }
        });

        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        //Send query to SearchActor & SearchMovie
        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO send this query to Search actor & Search movie fragment
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO send this query to Search actor & Search movie fragment
                return false;
            }
        });
    }

    /**
     * Change menu icon in toolbar showing list or grid view
     */
    private void updateToolbar() {
        layoutGrid = !layoutGrid;

        MenuItem gridView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_grid);
        gridView.setVisible(layoutGrid);
        MenuItem listView = mBinding.toolbar.getMenu().findItem(R.id.menu_switch_list);
        listView.setVisible(!layoutGrid);
    }


    private void switchLayout(RecyclerView.LayoutManager layoutManager) {
        //TODO implement a method to switch view of recyclerview owned by Searchable fragment
    }


   /* private void setupTabLayout() {
        final TabLayout tabLayout = mBinding.tabLayout;
        final ArrayList<String> titles = new ArrayList<>();
        titles.add("Movie");
        titles.add("Actor");
        new TabLayoutMediator(tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();
    }*/

    private void setupAppBar(@NonNull View view) {
        NavController navController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.searchFragment).build();
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }
    private void setupViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();//creates an ArrayList of Fragments
        fragments.add(new SearchMovieFragment());
        fragments.add(new SearchActorFragment());
        mAdapter = new ViewPagerAdapter(getParentFragmentManager());
        mBinding.viewPager.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}