package com.quran.administrator.quranapp.API;

import com.quran.administrator.quranapp.API.Responses.RadiosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIs {

    @GET("radio/radio_ar.json")
    Call<RadiosResponse> getRadioChannels();





}
