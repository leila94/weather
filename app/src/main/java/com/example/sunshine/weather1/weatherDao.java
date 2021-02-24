package com.example.sunshine.weather1;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sunshine.weather1.Entity1;
import com.example.sunshine.weather1.model.CityModel;

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

    @Query("SELECT cid FROM cityID WHERE id= :id")
    Single<Integer> getId2(int id);

    @Query("SELECT name FROM cityID")
    LiveData<List<String>> getNameArray();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCityId(List<JsonId> jsonId);
}
