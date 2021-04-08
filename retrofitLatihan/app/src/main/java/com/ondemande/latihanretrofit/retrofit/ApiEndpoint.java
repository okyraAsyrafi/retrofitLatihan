package com.ondemande.latihanretrofit.retrofit;

import com.ondemande.latihanretrofit.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("data.php")
    Call<MainModel> getListUsers();
}
