package org.milaifontanals.magicthegathering.api;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MagicApiService {

    @NonNull
    public static MagicAPI getMagicAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.magicthegathering.io/v1/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MagicAPI service = retrofit.create(MagicAPI.class);
        return service;
    }
}
