package com.example.cinemates.repository;

import androidx.annotation.NonNull;

import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Collection;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.MovieResponse;
import com.example.cinemates.model.VideoResponse;
import com.example.cinemates.network.MovieApiService;
import com.google.gson.JsonObject;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:46
 */
public class Repository {
    private static final String TAG = Repository.class.getSimpleName();

    MovieApiService apiService;


    @Inject
    public Repository(MovieApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<MovieResponse> getCurrentlyShowing(HashMap<String, String> map) {
        return apiService.getCurrentlyShowing(map);
    }

    public Observable<MovieResponse> getTrendingMovies(@NonNull String mediaType, @NonNull String timeWindow, HashMap<String, String> map) {
        return apiService.getTrendingMovies(mediaType, timeWindow, map);
    }

    public Observable<MovieResponse> getPopular(HashMap<String, String> map) {
        return apiService.getPopular(map);
    }

    public Observable<MovieResponse> getTopRated(HashMap<String, String> map) {
        return apiService.getTopRated(map);
    }

    public Observable<MovieResponse> getUpcoming(HashMap<String, String> map) {
        return apiService.getUpcoming(map);
    }

    public Observable<VideoResponse> getVideos(int movie_id, HashMap<String, String> map) {
        return apiService.getVideos(movie_id, map);
    }

    public Observable<Movie> getMovieDetails(int movieId, HashMap<String, String> map) {
        return apiService.getMovieDetails(movieId, map);
    }

    public Observable<MovieResponse> getSimilar(int movieId, HashMap<String, String> map) {
        return apiService.getSimilar(movieId, map);
    }

    public Observable<JsonObject> getCast(int movieId, HashMap<String, String> map) {
        return apiService.getCast(movieId, map);
    }

    public Observable<Actor> getActorDetails(int personId, HashMap<String, String> map) {
        return apiService.getActorDetails(personId, map);
    }

    public Observable<Collection> getCollection(int collectionId, HashMap<String, String> map) {
        return apiService.getCollection(collectionId, map);
    }

    public Observable<JsonObject> getMoviesBySearch(HashMap<String, String> map) {
        return apiService.getMoviesBySearch(map);

    }


}
