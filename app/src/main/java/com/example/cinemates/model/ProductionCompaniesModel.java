package com.example.cinemates.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 11:20
 */
public class ProductionCompaniesModel implements Parcelable {
    private String name;

    private ProductionCompaniesModel(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    protected ProductionCompaniesModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<ProductionCompaniesModel> CREATOR = new Creator<ProductionCompaniesModel>() {
        @Override
        public ProductionCompaniesModel createFromParcel(Parcel in) {
            return new ProductionCompaniesModel(in);
        }

        @Override
        public ProductionCompaniesModel[] newArray(int size) {
            return new ProductionCompaniesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

    @Override
    public String toString() {
        return "ProductionCompaniesModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
