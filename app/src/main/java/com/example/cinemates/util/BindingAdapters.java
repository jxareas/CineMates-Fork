package com.example.cinemates.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.cinemates.R;
import com.example.cinemates.model.api.CollectionModel;
import com.example.cinemates.model.api.ProductionCompaniesModel;
import com.example.cinemates.model.api.ProductionCountriesModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Antonio Di Nuzzo
 * Created 30/12/2021 at 07:51
 */
public class BindingAdapters {

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

    @BindingAdapter("visible")
    public static void setVisibility(View view, CollectionModel collectionModel) {
        view.setVisibility(collectionModel == null ? View.GONE: View.VISIBLE);
    }
}
