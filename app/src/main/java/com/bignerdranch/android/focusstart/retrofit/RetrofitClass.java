package com.bignerdranch.android.focusstart.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {

    public static final String BASE_URL = "https://www.cbr-xml-daily.ru/";

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

//    public APIservice apiService = retrofit.create(APIservice.class);

    public APIservice getApiService() {
        return retrofit.create(APIservice.class);
    }
}
