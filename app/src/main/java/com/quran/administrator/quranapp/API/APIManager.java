package com.quran.administrator.quranapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    private static Retrofit retrofitInstance;

    public static Retrofit getRetrofit(){
        if(retrofitInstance==null){
            //build
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("http://mp3quran.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

    public static APIs getAPIs(){

        return getRetrofit().create(APIs.class);
    }


}
