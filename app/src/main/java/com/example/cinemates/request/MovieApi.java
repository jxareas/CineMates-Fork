package com.example.cinemates.request;

import com.example.cinemates.model.api.CreditsModel;
import com.example.cinemates.model.api.MovieModel;
import com.example.cinemates.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //search for movies
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") int page,
            @Query("language") String language

    );

    //Get popular Movie
    //https://api.themoviedb.org/3/movie/popular
    // ?api_key=e3769daa94599884c8233ae849529479&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page,
            @Query("language") String language

    );


    //making search with ID
    // https://api.themoviedb.org/3/movie/550?api_key=e3769daa94599884c8233ae849529479
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language

    );

    // https://api.themoviedb.org/3/movie/{movie_id}/credits?api_key=<<api_key>>&language=en-US
    @GET("3/movie/{movie_id}/credits?")
    Call<CreditsModel> getCredits(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language

    );


}
