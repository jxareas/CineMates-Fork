package com.example.cinemates.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinemates.fragment.MediaCastFragment;
import com.example.cinemates.fragment.MediaInfoFragment;
import com.example.cinemates.fragment.SearchActorFragment;
import com.example.cinemates.fragment.SearchMovieFragment;

/**
 * @author Antonio Di Nuzzo
 * Created 05/12/2021 at 09:56
 */
public class MovieDetailActivityAdapter extends FragmentStateAdapter {
    public MovieDetailActivityAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new MediaInfoFragment();
            case 2:
                return new MediaCastFragment();
        }
        return new MediaInfoFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
