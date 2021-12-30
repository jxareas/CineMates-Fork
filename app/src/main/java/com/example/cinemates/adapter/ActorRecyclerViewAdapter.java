package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cinemates.databinding.ListItemPersonInformationBinding;
import com.example.cinemates.model.api.CastModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class ActorRecyclerViewAdapter extends EmptyRecyclerView.Adapter<ActorRecyclerViewAdapter.ActorViewHolder> {
    private List<CastModel> dataList;
    private LayoutInflater mLayoutInflater;

    public ActorRecyclerViewAdapter(List<CastModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemPersonInformationBinding mediaBinding = ListItemPersonInformationBinding.inflate(mLayoutInflater, parent, false);
        return new ActorViewHolder(mediaBinding);
    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        CastModel castModel = dataList.get(position);
        holder.mBinding.setCast(castModel);
        holder.mBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<CastModel> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class ActorViewHolder extends EmptyRecyclerView.ViewHolder {
        ListItemPersonInformationBinding mBinding;

        ActorViewHolder(@NonNull ListItemPersonInformationBinding listItemPersonInformationBinding) {
            super(listItemPersonInformationBinding.getRoot());
            this.mBinding = listItemPersonInformationBinding;


        }


    }
}