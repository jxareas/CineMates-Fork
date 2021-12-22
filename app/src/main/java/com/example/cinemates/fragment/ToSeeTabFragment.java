package com.example.cinemates.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.FragmentToSeeBinding;
import com.example.cinemates.interfaces.ChangeRvLayout;


public class ToSeeTabFragment extends Fragment implements ChangeRvLayout {


    private FragmentToSeeBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        mBinding = FragmentToSeeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void changeLayout(RecyclerView.LayoutManager layoutManager) {
        mBinding.recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(getContext(),"Layout modificato", Toast.LENGTH_SHORT).show();
    }
}