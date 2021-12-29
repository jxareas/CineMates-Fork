package com.example.cinemates.model.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 17:39
 */
public class CrewModel implements Parcelable {
    private int gender;
    private int id;
    private String known_for_department;
    private String name;
    private String original_name;
    private String profile_path;
    private String credit_id;
    private String department;
    private String job;


    private CrewModel(int gender, int id, String known_for_department, String name, String original_name, String profile_path, String credit_id, String department, String job) {
        this.gender = gender;
        this.id = id;
        this.known_for_department = known_for_department;
        this.name = name;
        this.original_name = original_name;
        this.profile_path = profile_path;
        this.credit_id = credit_id;
        this.department = department;
        this.job = job;
    }

    protected CrewModel(Parcel in) {
        gender = in.readInt();
        id = in.readInt();
        known_for_department = in.readString();
        name = in.readString();
        original_name = in.readString();
        profile_path = in.readString();
        credit_id = in.readString();
        department = in.readString();
        job = in.readString();
    }

    public static final Creator<CrewModel> CREATOR = new Creator<CrewModel>() {
        @Override
        public CrewModel createFromParcel(Parcel in) {
            return new CrewModel(in);
        }

        @Override
        public CrewModel[] newArray(int size) {
            return new CrewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(gender);
        parcel.writeString(known_for_department);
        parcel.writeString(name);
        parcel.writeString(original_name);
        parcel.writeString(profile_path);
        parcel.writeString(credit_id);
        parcel.writeString(department);
        parcel.writeString(job);
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getName() {
        return name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public String getDepartment() {
        return department;
    }

    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return "CrewModel{" +
                "gender=" + gender +
                ", id=" + id +
                ", known_for_department='" + known_for_department + '\'' +
                ", name='" + name + '\'' +
                ", original_name='" + original_name + '\'' +
                ", profile_path='" + profile_path + '\'' +
                ", credit_id='" + credit_id + '\'' +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
