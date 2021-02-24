package com.example.sunshine.weather1.repository;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.sunshine.weather1.JsonId;
import com.example.sunshine.weather1.WeatherService;
import com.example.sunshine.weather1.weatherDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private weatherDao weatherDao;
    private JsonReader reader;
    private SharedPreferences sharedPreferences;
    private WeatherService weatherService;

    @Inject
    public Repository(weatherDao weatherDao, JsonReader reader, SharedPreferences sharedPreferences, WeatherService weatherService) {
        this.weatherDao = weatherDao;
        this.reader = reader;
        this.sharedPreferences = sharedPreferences;
        this.weatherService = weatherService;
    }

    public LiveData<List<String >> getCities(){
        return weatherDao.getNameArray();
    }

    public Single<Integer> fetchWeather(int id){
        return weatherDao.getId2(id);
    }

    public void initializeDB() {
        if (sharedPreferences.getInt("DBinit", 0) == 0) {
            Gson gson = new GsonBuilder().create();
            JsonId cityModel;
            List<JsonId> ids = new ArrayList<>();

            try {
                reader.beginArray();

                while (reader.hasNext()) {
                    cityModel = gson.fromJson(reader, JsonId.class);
                    ids.add(cityModel);
                    Log.i("iii", cityModel.getJ());

                }

                //JsonId finalCityModel = cityModel;
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i("iii", ids.get(4).getJ());
                        //weatherDao dao = weatherDao.wDao();
                        weatherDao.insertCityId(ids);

                    }
                }).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        sharedPreferences.edit().putInt("DBinit",1).apply();
    }
}
