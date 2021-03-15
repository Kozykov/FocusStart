package com.bignerdranch.android.focusstart.retrofit;

import com.bignerdranch.android.focusstart.allvalues.GeneralInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIservice {
    @GET("daily_json.js")
    Call<GeneralInfo> loadRepo();
}
