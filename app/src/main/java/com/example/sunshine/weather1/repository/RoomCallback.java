package com.example.sunshine.weather1.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.sunshine.weather1.JsonId;
import com.example.sunshine.weather1.weatherDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class RoomCallback extends RoomDatabase.Callback {

    private Context mcontext;

    public RoomCallback(Context context) {
        mcontext = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        Gson gson = new GsonBuilder().create();
        JsonId cityModel;
        List<JsonId> ids = new ArrayList<>();

        try (JsonReader reader = new JsonReader(new InputStreamReader(mcontext.getAssets().open("ijCityList.json")))) {
            reader.beginArray();

            while (reader.hasNext()) {
                cityModel = gson.fromJson(reader, JsonId.class);
                ids.add(cityModel);

            }

            //JsonId finalCityModel = cityModel;
            Completable.fromAction(new Action() {
                @Override
                public void run() throws Exception {
                    Log.i("iii",ids.get(4).getJ());
                   // weatherDao dao = INSTANCE.wDao();
                  //  dao.insertCityId(ids);

                }
            }).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
