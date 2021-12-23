package com.example.cinemates.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 11:20
 */
public class ProductionCountriesModel implements Parcelable {
    private String name;

    private ProductionCountriesModel( String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    protected ProductionCountriesModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<ProductionCountriesModel> CREATOR = new Creator<ProductionCountriesModel>() {
        @Override
        public ProductionCountriesModel createFromParcel(Parcel in) {
            return new ProductionCountriesModel(in);
        }

        @Override
        public ProductionCountriesModel[] newArray(int size) {
            return new ProductionCountriesModel[size];
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
        return "ProductionCountriesModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
