package com.example.cinemates.viewModels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemates.model.CreditsModel;
import com.example.cinemates.repository.CreditsRepository;

public class CreditsViewModel extends ViewModel {

    private CreditsRepository mCreditsRepository;

    public CreditsViewModel() {
        mCreditsRepository = CreditsRepository.getInstance();
    }


    public LiveData<CreditsModel> getCredits() {
        return mCreditsRepository.getCredits();
    }


    public void searchCreditsByMovieId(int movie_id) {
        mCreditsRepository.searchCreditsByMovieId(movie_id);
    }


}
