package com.example.cinemates.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinemates.AppExecutors;
import com.example.cinemates.model.MovieModel;
import com.example.cinemates.response.MovieSearchResponse;
import com.example.cinemates.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    //Livedata for Search
    private MutableLiveData<List<MovieModel>> mMovies ;

    private static  MovieApiClient instance;

    //making Global Runnable
    private RetrieveMoviesRunnable mRetrieveMoviesRunnable;

    //Live data for popular movies
    private MutableLiveData<List<MovieModel>> mMoviesPop;

    //making popular Runnable
    private RetrieveMoviesRunnablePop mRetrieveMoviesRunnablePop;

    public static MovieApiClient getInstance(){
        if(instance==null){
            instance = new MovieApiClient();
        }
        return instance;
    }
    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
        mMoviesPop = new MutableLiveData<>();
    }


    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }
    public LiveData<List<MovieModel>> getMoviesPop() {
        return mMoviesPop;
    }

    public void searchMoviesApi(String query,int pageNumber){

        if(mRetrieveMoviesRunnable !=null){
            mRetrieveMoviesRunnable = null;
        }
        mRetrieveMoviesRunnable = new RetrieveMoviesRunnable(query,pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(mRetrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling the rertrofit call
                myHandler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);

}


    public void searchMoviesPop(int pageNumber) {

        if(mRetrieveMoviesRunnablePop !=null){
            mRetrieveMoviesRunnablePop = null;
        }
        mRetrieveMoviesRunnablePop = new RetrieveMoviesRunnablePop(pageNumber);

        final Future myHandler2 = AppExecutors.getInstance().networkIO().submit(mRetrieveMoviesRunnablePop);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling the rertrofit call
                myHandler2.cancel(true);
            }
        },1000, TimeUnit.MILLISECONDS);

    }


    //retrieving data from REST API by runnable class
    //We have 2 types of Queries: the ID & search Query
    private class RetrieveMoviesRunnable implements Runnable{

        private String query;
        private int pageNumber;
        boolean cancleRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancleRequest = false;
        }



        @Override
        public void run() {
            //getting the response Objects
            try{
                Response response = getMovies(query,pageNumber).execute();
                if (cancleRequest) {
                    return;
                }

                if(response.code()==200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber==1){
                        //sending data to live data
                        //postvalue: used for background thread
                        //setValue: not for background thread
                        mMovies.postValue(list);
                    }else {
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag","ERROR"+ error);
                    mMovies.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }



        }

            //search method query
            private Call<MovieSearchResponse>  getMovies(String query, int pageNumber){
                return Service.getMovieApi().searchMovie(
                        Constants.API_KEY,
                        query,
                       pageNumber
                );

            }
            private void cancleRequest() {
                Log.v("Tag", "cancelling Search Request");
                cancleRequest = true;
            }
    }



    private class RetrieveMoviesRunnablePop implements Runnable{

        private int pageNumber;
        boolean cancleRequest;

        public RetrieveMoviesRunnablePop(int pageNumber) {
            this.pageNumber = pageNumber;
            cancleRequest = false;
        }



        @Override
        public void run() {
            //getting the response Objects
            try{
                Response response2 = getPop(pageNumber).execute();
                if (cancleRequest) {
                    return;
                }

                if(response2.code()==200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response2.body()).getMovies());
                    if(pageNumber==1){
                        //sending data to live data
                        //postvalue: used for background thread
                        //setValue: not fkor background thread
                        mMoviesPop.postValue(list);
                    }else {
                        List<MovieModel> currentMovies = mMoviesPop.getValue();
                        currentMovies.addAll(list);
                        mMoviesPop.postValue(currentMovies);
                    }
                }else{
                    String error = response2.errorBody().string();
                    Log.v("Tag","ERROR"+ error);
                    mMoviesPop.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMoviesPop.postValue(null);
            }



        }

        //search method query
        private Call<MovieSearchResponse>  getPop(int pageNumber){
            return Service.getMovieApi().getPopular(
                    Constants.API_KEY,
                    pageNumber
            );

        }
        private void cancleRequest() {
            Log.v("Tag", "cancelling Search Request");
            cancleRequest = true;
        }
    }


}
