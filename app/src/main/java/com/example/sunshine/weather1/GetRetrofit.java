package com.example.sunshine.weather1;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leila on 5/12/2019.
 */

public class GetRetrofit {

    public static String BaseUrl = "http://api.openweathermap.org/";
    //public static String AppId = "2e65127e909e178d0af311a81f39948c";
    private static GetRetrofit instance = null;
    private WeatherService userService;



    public static GetRetrofit retrofitInstance() {
        if (instance == null) {
            instance = new GetRetrofit();

        }
        return instance;
                //create(WeatherService.class);
    }

    private GetRetrofit(){
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

       this.userService = retrofit.create(WeatherService.class);
    }

    private OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public WeatherService getUserService() {
        return this.userService;
    }

}
