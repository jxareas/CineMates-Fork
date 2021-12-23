package com.example.cinemates.model;

import android.os.Parcel;
import android.os.Parcelable;

import info.movito.themoviedbapi.model.Genre;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 11:20
 */
public class GenreModel {
    private int id;
    private String name;

    private GenreModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected GenreModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Parcelable.Creator<GenreModel> CREATOR = new Parcelable.Creator<GenreModel>() {
        @Override
        public GenreModel createFromParcel(Parcel in) {
            return new GenreModel(in);
        }

        @Override
        public GenreModel[] newArray(int size) {
            return new GenreModel[size];
        }
    };

    @Override
    public String toString() {
        return "GenreModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
