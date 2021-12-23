package com.example.cinemates.viewModels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemates.model.MovieModel;
import com.example.cinemates.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getPop() {
        return movieRepository.getPop();
    }

    public LiveData<MovieModel> getMovieSearchedById() {
        return movieRepository.getMovieSearched();
    }

    //2- calling Method in viewModel
    public void searchMovieApi(String query, int pageNumber) {
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchMoviePop(int pageNumber) {
        movieRepository.searchMoviePop(pageNumber);
    }

    public void searchMovieById(int movie_id) {
        movieRepository.searchMovieById(movie_id);
    }

    public void searchNextPage() {
        movieRepository.searchNextPage();
    }
}
