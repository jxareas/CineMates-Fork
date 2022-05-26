package com.example.cinemates.model;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:38
 */
public class Movie implements Serializable {

    private String poster_path, overview, release_date, title, backdrop_path, original_title, original_language, status;
    private Integer id, vote_count, runtime, budget;
    private Number popularity, vote_average;
    private ArrayList<Integer> genre_ids;
    private ArrayList<String> genre_names;
    private ArrayList<Genre> genres;
    private ArrayList<ProductionCompany> production_companies;
    private JsonObject videos;

    public Movie(String poster_path, String overview, String release_date, String title,
                 String original_title, String original_language, String status, Integer id, Integer vote_count, Integer budget, Number popularity, Number vote_average,
                 ArrayList<Integer> genre_ids, Integer runtime, ArrayList<Genre> genres,
                 String backdrop_path, ArrayList<ProductionCompany> production_companies, JsonObject videos) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.original_title = original_title;
        this.original_language = original_language;
        this.status = status;
        this.id = id;
        this.vote_count = vote_count;
        this.budget = budget;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.genre_ids = genre_ids;
        this.runtime = runtime;
        this.genres = genres;
        this.backdrop_path = backdrop_path;
        this.production_companies = production_companies;
        this.videos = videos;
    }

    public JsonObject getVideos() {
        return videos;
    }

    public ArrayList<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(ArrayList<ProductionCompany> production_companies) {
        this.production_companies = production_companies;
    }

    public void setVideos(JsonObject videos) {
        this.videos = videos;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public Integer getBudget() {
        return budget;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public void setVote_average(Number vote_average) {
        this.vote_average = vote_average;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public ArrayList<String> getGenre_names() {
        return genre_names;
    }

    public void setGenre_names(ArrayList<String> genre_names) {
        this.genre_names = genre_names;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", title='" + title + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", original_title='" + original_title + '\'' +
                ", original_language='" + original_language + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                ", vote_count=" + vote_count +
                ", runtime=" + runtime +
                ", budget=" + budget +
                ", popularity=" + popularity +
                ", vote_average=" + vote_average +
                ", genre_ids=" + genre_ids +
                ", genre_names=" + genre_names +
                ", genres=" + genres +
                ", production_companies=" + production_companies +
                ", videos=" + videos +
                '}';
    }
}
