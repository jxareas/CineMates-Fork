package com.example.cinemates.model.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.cinemates.R;
import com.example.cinemates.util.Constants;

/**
 * @author Antonio Di Nuzzo
 * Created 23/12/2021 at 11:20
 */
public class CastModel implements Parcelable {
    private int id;
    private int gender;
    private String know_for_department;
    private String name;
    private String original_name;
    private String profile_path;
    private int cast_id;
    private String character;
    private String credit_id;
    private int order;

    private CastModel(int id, int gender, String know_for_department, String name,
                      String original_name, String profile_path, int cast_id, String character, String credit_id, int order) {
        this.id = id;
        this.gender = gender;
        this.know_for_department = know_for_department;
        this.name = name;
        this.original_name = original_name;
        this.profile_path = profile_path;
        this.cast_id = cast_id;
        this.character = character;
        this.credit_id = credit_id;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getGender() {
        return gender;
    }

    public String getKnow_for_department() {
        return know_for_department;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public int getCast_id() {
        return cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public int getOrder() {
        return order;
    }

    protected CastModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        gender = in.readInt();
        know_for_department = in.readString();
        original_name = in.readString();
        profile_path = in.readString();
        cast_id = in.readInt();
        character = in.readString();
        credit_id = in.readString();
        order = in.readInt();
    }

    public static final Creator<CastModel> CREATOR = new Creator<CastModel>() {
        @Override
        public CastModel createFromParcel(Parcel in) {
            return new CastModel(in);
        }

        @Override
        public CastModel[] newArray(int size) {
            return new CastModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
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
        return "CastModel{" +
                "id=" + id +
                ", gender=" + gender +
                ", know_for_department='" + know_for_department + '\'' +
                ", name='" + name + '\'' +
                ", original_name='" + original_name + '\'' +
                ", profile_path='" + profile_path + '\'' +
                ", cast_id=" + cast_id +
                ", character='" + character + '\'' +
                ", credit_id='" + credit_id + '\'' +
                ", order=" + order +
                '}';
    }
}
