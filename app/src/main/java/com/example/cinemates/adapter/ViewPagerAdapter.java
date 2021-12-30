package com.example.cinemates.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 07:46
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;//variable holds the fragments the ViewPager2 allows us to swipe to.
    private List<String> mStringList;//variable holds the fragments the ViewPager2 allows us to swipe to.

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList, List<String> fragmentTitles) {
        super(fm);
        this.mFragments = fragmentList;
        this.mStringList = fragmentTitles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mStringList.get(position);
    }
}
