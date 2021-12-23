package com.example.cinemates.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemates.AppExecutors;
import com.example.cinemates.model.CreditsModel;
import com.example.cinemates.util.Constants;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class CreditsApiClient {


    private static CreditsApiClient instance;

    //making Global Runnable
    private RetrieveCreditsByMovieId mRetrieveCreditsByMovieId;


    //Live data for movie searched
    private MutableLiveData<CreditsModel> mCredits;


    public static CreditsApiClient getInstance() {
        if (instance == null) {
            instance = new CreditsApiClient();
        }
        return instance;
    }

    private CreditsApiClient() {
        mCredits = new MutableLiveData<>();
    }

    public LiveData<CreditsModel> getCredits() {
        return mCredits;
    }


    public void searchCreditsById(int movie_id) {

        if (mRetrieveCreditsByMovieId != null) {
            mRetrieveCreditsByMovieId = null;
        }
        mRetrieveCreditsByMovieId = new RetrieveCreditsByMovieId(movie_id);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(mRetrieveCreditsByMovieId);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling the rertrofit call
                myHandler.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);

    }




    private class RetrieveCreditsByMovieId implements Runnable {

        private int movie_id;
        boolean cancleRequest;

        public RetrieveCreditsByMovieId(int movie_id) {
            this.movie_id = movie_id;
            cancleRequest = false;
        }


        @Override
        public void run() {
            //getting the response Objects
            try {
                Response response = getCredits(movie_id).execute();
                if (cancleRequest) {
                    return;
                }

                if (response.code() == 200) {
                    CreditsModel creditsModel = (CreditsModel) response.body();
                    mCredits.postValue(creditsModel);

                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "ERROR" + error);
                    mCredits.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mCredits.postValue(null);
            }


        }

        //search method query
        private Call<CreditsModel> getCredits(int movie_id) {
            return Service.getMovieApi().getCredits(
                    movie_id,
                    Constants.API_KEY
            );

        }

        private void cancleRequest() {
            Log.v("Tag", "cancelling Search Request");
            cancleRequest = true;
        }
    }


}
