package com.example.cinemates.repository;

import androidx.lifecycle.LiveData;

import com.example.cinemates.model.api.MovieModel;
import com.example.cinemates.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;
    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }

    public LiveData<List<MovieModel>> getPop() {
        return movieApiClient.getMoviesPop();
    }

    public LiveData<MovieModel> getMovieSearched() {
        return movieApiClient.getMovieSearched();
    }

    //calling the method
    public void searchMovieApi(String query, int pageNumber) {

        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    public void searchMoviePop(int pageNumber) {
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPop(pageNumber);
    }

    public void searchMovieById(int movie_id) {
        movieApiClient.searchMovieById(movie_id);
    }


    public void searchNextPage() {
        searchMovieApi(mQuery, mPageNumber + 1);
    }


}



















