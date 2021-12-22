package com.example.cinemates;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemates.databinding.ActivityDetailMediaContentBinding;
import com.example.cinemates.model.MovieModel;

public class DetailMediaContentActivity extends AppCompatActivity {

    private ActivityDetailMediaContentBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityDetailMediaContentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getDataFromIntent();

    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            mBinding.setMovie(movieModel);

        }

    }

}