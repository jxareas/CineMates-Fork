package com.example.cinemates.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.R;
import com.example.cinemates.databinding.ListItemPersonInformationBinding;
import com.example.cinemates.databinding.SectionRowBinding;
import com.example.cinemates.fragment.HomeFragmentDirections;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.MovieModel;
import com.example.cinemates.model.Section;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.people.PersonCast;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {
    private final List<Section> dataList = new ArrayList<>();
    private static final String TAG = SectionItemsRecyclerViewAdapter.class.getSimpleName();

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SectionRowBinding sectionRowBinding = SectionRowBinding.inflate(layoutInflater, parent, false);
        return new SectionViewHolder(sectionRowBinding);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Section section = dataList.get(position);
        holder.mBinding.setSection(section);
        holder.mBinding.executePendingBindings();
        List<MovieModel> movies_of_section = section.getSectionItems();

        SectionItemsRecyclerViewAdapter sectionItemsRecyclerViewAdapter = new SectionItemsRecyclerViewAdapter();
        sectionItemsRecyclerViewAdapter.addItems(movies_of_section);
        holder.mBinding.recyclerView.setAdapter(sectionItemsRecyclerViewAdapter);
        holder.mBinding.recyclerView.setEmptyView(holder.mBinding.emptyView.getRoot());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Section> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class SectionViewHolder extends EmptyRecyclerView.ViewHolder {
        SectionRowBinding mBinding;

        SectionViewHolder(@NonNull SectionRowBinding sectionRowBinding) {
            super(sectionRowBinding.getRoot());
            this.mBinding = sectionRowBinding;

            this.mBinding.actionOpenPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HomeFragmentDirections.ActionHomeFragmentToDetailedViewFragment action =
                                HomeFragmentDirections.actionHomeFragmentToDetailedViewFragment();
                    action.setSection(mBinding.textSectionTitle.getText().toString());
                    Navigation.findNavController(view).navigate(action);

                }
            });


        }


    }
}