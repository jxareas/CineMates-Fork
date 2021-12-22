package com.example.cinemates.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.cinemates.R;
import com.example.cinemates.util.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {
    //model class for our movies
    private String title;
    private String poster_path;
    private String release_date;
    private int movie_id;
    private float vote_average;
    private String years;
    private int duration;
    private String plot;
    @SerializedName("overview")
    @Expose
    private String movie_overview;
    private String original_language;


    //for the purpose of simplicity ,i will use the release date  instead of category
    //genres is nested json object , we will learn it later in this series


    private MovieModel(String title, String poster_path, String release_date, int movie_id, float vote_average, String years, int duration, String plot, String movie_overview, String original_language) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.years = years;
        this.duration = duration;
        this.plot = plot;
        this.movie_overview = movie_overview;
        this.original_language = original_language;
    }


    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
        original_language = in.readString();
        years = in.readString();
        duration = in.readInt();
        plot = in.readString();

    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    private String getYears() {
        return years;
    }

    private int getDuration() {
        return duration;
    }

    private String getPlot() {
        return plot;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }


    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
        dest.writeString(original_language);
        dest.writeString(poster_path);
        dest.writeString(years);
        dest.writeString(plot);
        dest.writeInt(duration);
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String posterPath) {
        Glide.with(imageView)
                .load(Constants.POSTER_URL + posterPath)
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .into(imageView);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", movie_id=" + movie_id +
                ", vote_average=" + vote_average +
                ", years='" + years + '\'' +
                ", duration=" + duration +
                ", plot='" + plot + '\'' +
                ", movie_overview='" + movie_overview + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
