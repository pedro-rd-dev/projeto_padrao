package com.projeto_padrao.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl("https://escolarap.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UserService setUserService() {
        return this.retrofit.create(UserService.class);
    }
}
