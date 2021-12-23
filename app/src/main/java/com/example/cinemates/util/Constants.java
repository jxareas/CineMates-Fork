package com.example.cinemates.util;


import static java.util.Locale.getDefault;

import com.example.cinemates.BuildConfig;

public class Constants {

    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String POSTER_URL = "https://image.tmdb.org/t/p/original/";
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String DEFAULT_SYSTEM_LANGUAGE = getDefault().getLanguage();

    public static boolean POPULAR = true;


}
