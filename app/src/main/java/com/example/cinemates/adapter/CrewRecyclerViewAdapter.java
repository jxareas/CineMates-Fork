package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemCrewBinding;
import com.example.cinemates.model.api.CrewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class CrewRecyclerViewAdapter extends RecyclerView.Adapter<CrewRecyclerViewAdapter.CrewViewHolder> {
    private  List<CrewModel> dataList ;
    private LayoutInflater mLayoutInflater;
    private static final int ITEMS_LIMIT  = 4;

    public CrewRecyclerViewAdapter(List<CrewModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null){
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemCrewBinding listItemCrewBinding = ListItemCrewBinding.inflate(mLayoutInflater, parent, false);
        return new CrewViewHolder(listItemCrewBinding);
    }

    @Override
    public void onBindViewHolder(CrewViewHolder holder, int position) {
        CrewModel crewModel = dataList.get(position);
        holder.mBinding.setCrew(crewModel);
        holder.mBinding.executePendingBindings();

        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO navigate to a fragment of crewModel
                Toast.makeText(view.getContext(), "Clicked crewModel: "+crewModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        //Return number of items into the limit
        return Math.min(dataList.size(), ITEMS_LIMIT);
    }

   /* public void addItems(List<CrewModel> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }*/

    static class CrewViewHolder extends RecyclerView.ViewHolder {
        ListItemCrewBinding mBinding;

        CrewViewHolder(@NonNull ListItemCrewBinding listItemCrewBinding) {
            super(listItemCrewBinding.getRoot());
            this.mBinding = listItemCrewBinding;


        }


    }
}