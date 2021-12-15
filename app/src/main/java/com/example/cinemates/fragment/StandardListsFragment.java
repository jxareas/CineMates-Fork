package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cinemates.R;
import com.example.cinemates.adapter.FragmentStandardListsAdapter;
import com.example.cinemates.databinding.FragmentStandardListsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class StandardListsFragment extends Fragment {

    private FragmentStandardListsBinding mBinding;
    private FragmentStandardListsAdapter mFragmentStandardListsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentStandardListsAdapter = new FragmentStandardListsAdapter(getParentFragmentManager(), getLifecycle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStandardListsBinding.inflate(inflater, container, false);

        mBinding.viewPager.setAdapter(mFragmentStandardListsAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        setupAppBar(view);

        setupTabLayout();
    }

    private void setupTabLayout() {
        TabLayout tabLayout = mBinding.tabLayout;
        new TabLayoutMediator(tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("To See");
                        break;
                    case 1:
                        tab.setText("Seen");
                        break;
                }
            }
        }).attach();
    }

    private void setupAppBar(@NonNull View view) {
        NavController navController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.standardListsFragment).build();
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}