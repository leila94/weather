package com.example.sunshine.weather1;


import android.Manifest;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by Leila on 5/12/2019.
 */

public class WeatherViewModel extends AndroidViewModel {
    private LiveData<List<Entity1>> liveDataEntity;
    private weatherDao weatherDao;
    private int dbC;
    private int id1;
    private WeatherResponse weatherResponse1;
    private Response2 response2;

    public WeatherViewModel(@NonNull Application application) {
        super(application);


        AppDatabase database = AppDatabase.getAppDatabase(application);
        weatherDao = database.wDao();
        liveDataEntity = weatherDao.readAll();
    }

    public Single<Integer> getIranids(String string){
        return weatherDao.getId2(string).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    public Single<List<String >> getIranNames(){
        return weatherDao.getNameArray().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public LiveData<List<Entity1>> getAllWeather() {
        return liveDataEntity;
    }


    public void insertEntity(final Entity1 entity1) {

        //Single<Entity1>
        Single.create(new SingleOnSubscribe<Entity1>() {
            @Override
            public void subscribe(SingleEmitter<Entity1> emitter) throws Exception {
                emitter.onSuccess(entity1);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io())
                .subscribe(new Consumer<Entity1>() {
                    @Override
                    public void accept(Entity1 entity1) throws Exception {
                        weatherDao.insertCity(entity1);
                    }
                });
    }

    public void updateEntity(final Entity1 entity1) {
        Single.create(new SingleOnSubscribe<Entity1>() {
            @Override
            public void subscribe(SingleEmitter<Entity1> emitter) throws Exception {
                emitter.onSuccess(entity1);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io())
                .subscribe(new Consumer<Entity1>() {
                    @Override
                    public void accept(Entity1 entity1) throws Exception {
                        weatherDao.updateWeather(entity1);
                    }
                });
    }

    public void deleteEntity(final Entity1 entity1) {
        Single.create(new SingleOnSubscribe<Entity1>() {
            @Override
            public void subscribe(SingleEmitter<Entity1> emitter) throws Exception {
                emitter.onSuccess(entity1);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io())
                .subscribe(new Consumer<Entity1>() {
                    @Override
                    public void accept(Entity1 entity1) throws Exception {
                        weatherDao.deleteCity(entity1);
                    }
                });
    }


    public void deleteAll() {

        Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(1);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        weatherDao.deleteAllCities();
                    }

                });

    }



    public Single<WeatherResponse> fetchWeather(final int id, final int primaryId) {

        return GetRetrofit.retrofitInstance().getUserService()
                .getCurrentWeatherData(String.valueOf(id), "2e65127e909e178d0af311a81f39948c", "metric")
                .subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());


    }


    public Single<Response2> fetchForecast(int id) {
        return GetRetrofit.retrofitInstance().getUserService()
                .getNextData(String.valueOf(id), "2e65127e909e178d0af311a81f39948c", "metric")
                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());


    }

}
