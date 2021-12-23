package com.example.cinemates.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.cinemates.R;
import com.example.cinemates.util.Constants;

import java.text.CollationKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 13:15
 */
public class CollectionModel implements Parcelable {

    private int id;
    private String name;
    private String overview;
    private String backdrop_path;
    private List<MovieModel> parts;

    private CollectionModel(int id, String name, String overview, String backdrop_path, List<MovieModel> parts) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.parts = parts;
    }

    protected CollectionModel(Parcel in) {
        in.writeString(name);
        in.writeInt(id);
        in.writeString(overview);
        in.writeString(backdrop_path);
        parts = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<MovieModel> getParts() {
        return parts;
    }

    public static final Parcelable.Creator<CollectionModel> CREATOR = new Parcelable.Creator<CollectionModel>() {
        @Override
        public CollectionModel createFromParcel(Parcel in) {
            return new CollectionModel(in);
        }

        @Override
        public CollectionModel[] newArray(int size) {
            return new CollectionModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(overview);
        dest.writeString(backdrop_path);
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeList(parts);
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String posterPath) {
        Glide.with(imageView)
                .load(Constants.POSTER_URL + posterPath)
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .into(imageView);
    }

    @BindingAdapter("visible")
    public static void setVisibility(View view, CollectionModel collectionModel) {
        view.setVisibility(collectionModel == null ? View.GONE: View.VISIBLE);
    }


    @Override
    public String toString() {
        return "CollectionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", parts=" + parts +
                '}';
    }
}
