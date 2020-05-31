package com.devlab.tamboon.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private String baseUrl = "http://34.72.59.111/";
    private Retrofit retrofit  = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RetrofitService(){};

    private static class RetrofitServiceHelper{
        private static final RetrofitService RETROFIT_SERVICE_INSTANCE = new RetrofitService();
    }

    public static RetrofitService getInstance(){
        return RetrofitServiceHelper.RETROFIT_SERVICE_INSTANCE;
    }

    public <T> T create(Class<T> serviceClass){
        return retrofit.create(serviceClass);
    }
}
