package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.cinemates.databinding.FragmentStandardListsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;


public class StandardListsFragment extends Fragment {

    private FragmentStandardListsBinding mBinding;
    private ViewPagerAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean layoutGrid = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
        mLinearLayoutManager = new LinearLayoutManager(getContext());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStandardListsBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupAppBar(view);
        setupViewPager2();
//        setupTabLayout();

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
    }

    private void switchLayout(RecyclerView.LayoutManager layoutManager) {
        //TODO implement a method to switch view of recyclerview owned by Standard lists fragment
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

    /*private void setupTabLayout() {
        final TabLayout tabLayout = mBinding.tabLayout;
        final ArrayList<String> titles = new ArrayList<>();
        titles.add("To See");
        titles.add("Seen");
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
                new AppBarConfiguration.Builder(R.id.standardListsFragment).build();
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }
    private void setupViewPager2() {
        ArrayList<Fragment> fragments = new ArrayList<>();//creates an ArrayList of Fragments
        fragments.add(new SearchMovieFragment());
        fragments.add(new SearchActorFragment());
        mAdapter = new ViewPagerAdapter(getParentFragmentManager(), fragments, Arrays.asList("Movie", "Actor"));
        mBinding.viewPager.setAdapter(mAdapter);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_change_view, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_switch_grid:
                Toast.makeText(getContext(), "Grid", Toast.LENGTH_SHORT).show();

                //hide menu switch grid
                //show menu switch list
                //change layout manager
                break;
            case R.id.menu_switch_list:
                Toast.makeText(getContext(), "List", Toast.LENGTH_SHORT).show();
                //hide menu switch list
                //show menu switch grid
                //change layout manager
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}