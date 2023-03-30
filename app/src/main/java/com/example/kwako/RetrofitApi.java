package com.example.kwako;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApi {
    @POST("express/initialize")
    Call<DataModal> createPost(
            @Header("Apikey") String apikey,
            @Header("Accept") String acceptHeader,
            @Body DataModal data

    );

}