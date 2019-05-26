package com.example.sunshine.weather1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.sunshine.weather1.Entity1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Leila on 4/24/2019.
 */

@Dao
public interface weatherDao {

    @Insert
    void insertCity (Entity1 entity1);

    @Query("SELECT * FROM parms WHERE cityName = :name")
    Entity1 findWeather(String name);

    @Query("SELECT * FROM parms")
    LiveData<List<Entity1>> readAll();

    @Update
    void updateWeather(Entity1 entity1);

    @Delete
    void deleteCity (Entity1 entity1);

    @Query("DELETE FROM cityID")
    void deleteAllCities ();

    @Query("SELECT cid FROM cityID WHERE name= :name")
    Single<Integer> getId2(String name);

    @Query("SELECT name FROM cityID")
    Single<List<String>> getNameArray();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCityId(List<JsonId> jsonIds);
}
