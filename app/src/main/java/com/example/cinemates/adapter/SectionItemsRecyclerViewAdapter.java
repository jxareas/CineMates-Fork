package com.example.cinemates.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cinemates.DetailMediaContentActivity;
import com.example.cinemates.databinding.ListItemMediaPosterBinding;
import com.example.cinemates.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionItemsRecyclerViewAdapter extends EmptyRecyclerView.Adapter<SectionItemsRecyclerViewAdapter.SectionItemViewHolder> {
    private final List<MovieModel> dataList = new ArrayList<>();

    @NonNull
    @Override
    public SectionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemMediaPosterBinding mediaBinding = ListItemMediaPosterBinding.inflate(layoutInflater, parent, false);
        return new SectionItemViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionItemViewHolder holder, int position) {
        MovieModel movie = dataList.get(position);
        holder.mBinding.setMovie(movie);
        holder.mBinding.executePendingBindings();
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailMediaContentActivity.class);
                intent.putExtra("movie", movie);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<MovieModel> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class SectionItemViewHolder extends EmptyRecyclerView.ViewHolder {
        ListItemMediaPosterBinding mBinding;

        SectionItemViewHolder(@NonNull ListItemMediaPosterBinding listItemMediaPosterBinding) {
            super(listItemMediaPosterBinding.getRoot());
            this.mBinding = listItemMediaPosterBinding;

            this.mBinding.iconMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO open bottom sheet
                }
            });

            this.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO open detail activity
                }
            });

        }


    }
}