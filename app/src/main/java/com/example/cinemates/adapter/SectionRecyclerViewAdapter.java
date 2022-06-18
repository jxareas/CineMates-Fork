package com.example.cinemates.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.SectionRowBinding;
import com.example.cinemates.model.Movie;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.example.cinemates.views.fragment.HomeFragmentDirections;
import com.example.cinemates.model.Section;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionRecyclerViewAdapter<T> extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {
    private final List<Section<T>> dataList = new ArrayList<>();
    private final LifecycleOwner mLifecycleOwner;

    public SectionRecyclerViewAdapter(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SectionRowBinding sectionRowBinding = SectionRowBinding.inflate(layoutInflater, parent, false);
        return new SectionViewHolder(sectionRowBinding);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Section<T> section = dataList.get(position);
        holder.mBinding.setSection(section);
        holder.mBinding.executePendingBindings();

        SectionItemsRecyclerViewAdapter<T> sectionItemsRecyclerViewAdapter = new SectionItemsRecyclerViewAdapter<>();
        holder.mBinding.recyclerView.setAdapter(sectionItemsRecyclerViewAdapter);
        holder.mBinding.recyclerView.setEmptyView(holder.mBinding.emptyView.getRoot());
        section.getMutableLiveData().observe(mLifecycleOwner, new Observer<ArrayList<T>>() {
            @Override
            public void onChanged(ArrayList<T> items) {
                sectionItemsRecyclerViewAdapter.addItems((items));

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Section<T>> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addItems(Section<T> section) {
        this.dataList.add(section);
        notifyDataSetChanged();
    }

    static class SectionViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        SectionRowBinding mBinding;

        SectionViewHolder(@NonNull SectionRowBinding sectionRowBinding) {
            super(sectionRowBinding.getRoot());
            this.mBinding = sectionRowBinding;

            this.mBinding.actionOpenPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  /*  HomeFragmentDirections.ActionHomeFragmentToDetailedViewFragment action =
                            HomeFragmentDirections.actionHomeFragmentToDetailedViewFragment();
                    action.setSection(mBinding.textSectionTitle.getText().toString());
                    Navigation.findNavController(view).navigate(action);*/
                    Toast.makeText(view.getContext(), "On develop", Toast.LENGTH_SHORT).show();

                }
            });


        }


    }
}