package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ItemChipTextBinding;
import com.example.cinemates.model.GenreModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class GenreRecyclerViewAdapter extends RecyclerView.Adapter<GenreRecyclerViewAdapter.GenreViewHolder> {
    private final List<GenreModel> dataList = new ArrayList<>();

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemChipTextBinding itemChipTextBinding = ItemChipTextBinding.inflate(layoutInflater, parent, false);
        return new GenreViewHolder(itemChipTextBinding);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        GenreModel genre = dataList.get(position);
        holder.mBinding.setGenre(genre);
        holder.mBinding.executePendingBindings();

        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO navigate to a fragment of genre
                Toast.makeText(view.getContext(), "Clicked genre: "+genre.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<GenreModel> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class GenreViewHolder extends RecyclerView.ViewHolder {
        ItemChipTextBinding mBinding;

        GenreViewHolder(@NonNull ItemChipTextBinding itemChipTextBinding) {
            super(itemChipTextBinding.getRoot());
            this.mBinding = itemChipTextBinding;


        }


    }
}