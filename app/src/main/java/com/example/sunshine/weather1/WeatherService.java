package com.example.sunshine.weather1;

import com.example.sunshine.weather1.WeatherResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leila on 4/7/2019.
 */

public interface WeatherService {

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getWeatherByCoordinate(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    @GET("data/2.5/weather?")
    Single<WeatherResponse> getCurrentWeatherData(@Query("id") String id, @Query("appid") String app_id, @Query("units") String metric);

    @GET("data/2.5/forecast?")
    Single<Response2> getNextData(@Query("id") String lat, @Query("APPID") String app_id, @Query("units") String metric);
}
