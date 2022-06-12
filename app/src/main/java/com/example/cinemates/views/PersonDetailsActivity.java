package com.example.cinemates.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.cinemates.R;
import com.example.cinemates.databinding.ActivityPersonDetailsBinding;
import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Person;
import com.example.cinemates.viewmodel.MovieViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PersonDetailsActivity extends AppCompatActivity {

    private ActivityPersonDetailsBinding binding;
    private Person mPerson;
    private MovieViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPersonDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mPerson = (Person) getIntent().getExtras().getSerializable("person");

        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mViewModel.getActor().observe(this, new Observer<Actor>() {
            @Override
            public void onChanged(Actor actor) {
                binding.setActor(actor);
            }
        });
        mViewModel.getActorDetails(mPerson.getId());
    }


}