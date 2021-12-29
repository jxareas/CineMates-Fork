package com.example.cinemates.model.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.cinemates.R;
import com.example.cinemates.util.Constants;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieModel implements Parcelable {
    //model class for our movies
    private String title;
    private String original_title;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private int id;
    private float vote_average;
    private String years;
    private int runtime;
    private String overview;
    private String original_language;
    private String status;
    private int budget;
    private int revenue;
    private List<GenreModel> genres;
    private List<ProductionCountriesModel> production_countries;
    private List<ProductionCompaniesModel> production_companies;
    private CollectionModel belongs_to_collection;


    //for the purpose of simplicity ,i will use the release date  instead of category
    //genres is nested json object , we will learn it later in this series


    private MovieModel(String title, String original_title, String poster_path, String backdrop_path,
                       String release_date, int id, float vote_average, String years, int runtime,
                       String overview, String original_language, String status, int budget, List<GenreModel> genres,
                       int revenue, CollectionModel collectionModel, List<ProductionCountriesModel> production_countries,
                       List<ProductionCompaniesModel> production_companies) {
        this.title = title;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.id = id;
        this.vote_average = vote_average;
        this.years = years;
        this.runtime = runtime;
        this.overview = overview;
        this.original_language = original_language;
        this.status = status;
        this.budget = budget;
        this.genres = genres;
        this.revenue = revenue;
        this.belongs_to_collection = collectionModel;
        this.production_countries = production_countries;
        this.production_companies = production_companies;

    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        id = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
        original_language = in.readString();
        years = in.readString();
        runtime = in.readInt();
        backdrop_path = in.readString();
        original_title = in.readString();
        budget = in.readInt();
        status = in.readString();
        genres = new ArrayList<>();
        production_countries = new ArrayList<>();
        production_companies = new ArrayList<>();
        revenue = in.readInt();
        belongs_to_collection = in.readParcelable(CollectionModel.class.getClassLoader());
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

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public int getBudget() {
        return budget;
    }

    public List<ProductionCountriesModel> getProduction_countries() {
        return production_countries;
    }

    public int getRevenue() {
        return revenue;
    }

    public List<ProductionCompaniesModel> getProduction_companies() {
        return production_companies;
    }

    public String getYears() {
        return years;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<GenreModel> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public CollectionModel getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
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
        dest.writeInt(id);
        dest.writeFloat(vote_average);
        dest.writeString(overview);
        dest.writeString(original_language);
        dest.writeString(poster_path);
        dest.writeString(years);
        dest.writeInt(runtime);
        dest.writeString(original_title);
        dest.writeString(backdrop_path);
        dest.writeString(status);
        dest.writeInt(budget);
        dest.writeList(genres);
        dest.writeList(production_countries);
        dest.writeList(production_companies);
        dest.writeInt(revenue);
        dest.writeParcelable(belongs_to_collection, i);

    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String posterPath) {
        Glide.with(imageView)
                .load(Constants.POSTER_URL + posterPath)
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .into(imageView);
    }

    @BindingAdapter("setRuntime")
    public static void setRuntime(TextView textView, int runtime) {
        int hours = runtime / 60; //since both are ints, you get an int
        int minutes = runtime % 60;
        String formatted_runtime = hours + " hours " + minutes + " min";
        textView.setText(formatted_runtime);
    }

    @BindingAdapter("setCurrency")
    public static void setFormattedCurrency(TextView textView, float value) {
        textView.setText(NumberFormat.getCurrencyInstance(Locale.getDefault()).format(value));
    }

    @BindingAdapter("setProductionCountries")
    public static void setProductionCountries(TextView textView, List<ProductionCountriesModel> list) {
        String value = "";
        for (ProductionCountriesModel pc : list) {
            value += pc.getName() + "\n";
        }
        textView.setText(value);
    }

    @BindingAdapter("setProductionCompanies")
    public static void setProductionCompanies(TextView textView, List<ProductionCompaniesModel> list) {
        String value = "";
        for (ProductionCompaniesModel pc : list) {
            value += pc.getName() + "\n";
        }
        textView.setText(value);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", id=" + id +
                ", vote_average=" + vote_average +
                ", years='" + years + '\'' +
                ", runtime=" + runtime +
                ", overview='" + overview + '\'' +
                ", original_language='" + original_language + '\'' +
                ", status='" + status + '\'' +
                ", budget=" + budget +
                ", revenue=" + revenue +
                ", genres=" + genres +
                ", production_countries=" + production_countries +
                ", belongs_to_collection=" + belongs_to_collection +
                '}';
    }
}
