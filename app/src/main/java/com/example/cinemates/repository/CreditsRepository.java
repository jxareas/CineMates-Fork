package com.example.cinemates.repository;

import androidx.lifecycle.LiveData;

import com.example.cinemates.model.CreditsModel;
import com.example.cinemates.model.MovieModel;
import com.example.cinemates.request.CreditsApiClient;
import com.example.cinemates.request.MovieApiClient;

import java.util.List;

public class CreditsRepository {

    private static CreditsRepository instance;

    private CreditsApiClient mCreditsApiClient;

    public static CreditsRepository getInstance() {
        if (instance == null) {
            instance = new CreditsRepository();
        }
        return instance;
    }

    private CreditsRepository() {
        mCreditsApiClient = CreditsApiClient.getInstance();
    }

    public LiveData<CreditsModel> getCredits() {
        return mCreditsApiClient.getCredits();
    }


    public void searchCreditsByMovieId(int movie_id) {
        mCreditsApiClient.searchCreditsById(movie_id);
    }


}



















