package com.example.cinemates.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.example.cinemates.R;
import com.example.cinemates.databinding.ListItemMediaPosterBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.example.cinemates.views.fragment.HomeFragmentDirections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class MovieRecyclerViewAdapter extends RecyclerViewEmptySupport.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {
    private List<Movie> dataList = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemMediaPosterBinding mediaBinding = ListItemMediaPosterBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = dataList.get(position);
        holder.mBinding.setMovie(movie);
        holder.mBinding.executePendingBindings();
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    HomeFragmentDirections.ActionHomeFragmentToDetailMediaContentFragment action =
                            HomeFragmentDirections.actionHomeFragmentToDetailMediaContentFragment(movie);
                    Navigation.findNavController(view).navigate(action);

                }catch (IllegalArgumentException exception){
                    Toast.makeText(view.getContext(), "Impossibile per adesso", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Movie> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        private static final String TAG = MovieViewHolder.class.getSimpleName();
        ListItemMediaPosterBinding mBinding;

        MovieViewHolder(@NonNull ListItemMediaPosterBinding listItemMediaPosterBinding) {
            super(listItemMediaPosterBinding.getRoot());
            this.mBinding = listItemMediaPosterBinding;

        }


    }
}