package com.example.cinemates.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 17:36
 */
public class CreditsModel implements Parcelable {
    private int id;
    private List<CastModel> cast;
    private List<CrewModel> crew;

    private CreditsModel(int id, List<CastModel> castModelList, List<CrewModel> crewModelsList) {
        this.id = id;
        cast = castModelList;
        crew = crewModelsList;
    }

    protected CreditsModel(Parcel in) {
        id = in.readInt();
        cast = new ArrayList<>();
        crew = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<CastModel> getCast() {
        return cast;
    }

    public List<CrewModel> getCrew() {
        return crew;
    }

    public static final Creator<CreditsModel> CREATOR = new Creator<CreditsModel>() {
        @Override
        public CreditsModel createFromParcel(Parcel in) {
            return new CreditsModel(in);
        }

        @Override
        public CreditsModel[] newArray(int size) {
            return new CreditsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeList(cast);
        parcel.writeList(crew);
    }

    @Override
    public String toString() {
        return "CreditsModel{" +
                "id=" + id +
                ", mCastModelList=" + cast +
                ", mCrewModelsList=" + crew +
                '}';
    }
}
