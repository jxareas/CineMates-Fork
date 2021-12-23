package com.example.cinemates.response;


import com.example.cinemates.model.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//this class is for requesting single movie object
public class MovieResponse {
    // 1-finding  the movie object

    @SerializedName("results")
    @Expose()
    private MovieModel movie;

    @SerializedName("genres")
    @Expose
    private List<Integer> genres;

    public MovieModel getMovie() {
        return movie;
    }

    public List<Integer> getGenres() {
        return genres;
    }


    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                ", genres=" + genres +
                '}';
    }
}
