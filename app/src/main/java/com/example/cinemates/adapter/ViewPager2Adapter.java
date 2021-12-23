package com.example.cinemates.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 07:46
 */
public class ViewPager2Adapter extends FragmentStateAdapter {
    private List<Fragment> mFragments;//variable holds the fragments the ViewPager2 allows us to swipe to.

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }

    public void setData(List<Fragment> fragments){
        this.mFragments = fragments;
    }
}
