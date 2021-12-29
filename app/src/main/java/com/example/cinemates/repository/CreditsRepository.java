package com.example.cinemates.repository;

import androidx.lifecycle.LiveData;

import com.example.cinemates.model.api.CreditsModel;
import com.example.cinemates.request.CreditsApiClient;

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



















