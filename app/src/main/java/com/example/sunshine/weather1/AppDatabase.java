package com.example.sunshine.weather1;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Leila on 4/24/2019.
 */

@Database(entities = {Entity1.class, JsonId.class} , version = 4,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract weatherDao wDao() ;
    private static AppDatabase INSTANCE;

    //private AppDatabase(){}



    public static synchronized AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "weatherBase")
                            .fallbackToDestructiveMigration()
                            .addCallback(rdc)
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    private static RoomDatabase.Callback rdc = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            List<JsonId> jsonIds = new ArrayList<>();
            jsonIds.add(new JsonId(66093,"Yasuj"));
            jsonIds.add(new JsonId(144448,"Ahvaz"));
            jsonIds.add(new JsonId(141681,"Bandar Abbas"));
            jsonIds.add(new JsonId(126914,"Kish"));
            jsonIds.add(new JsonId(121801,"Orumiyeh"));
            jsonIds.add(new JsonId(127349,"Khorramabad"));
            jsonIds.add(new JsonId(117574,"Sanandaj"));
            jsonIds.add(new JsonId(119208,"Qom"));
            jsonIds.add(new JsonId(111822,"Yazd"));
            jsonIds.add(new JsonId(132892,"Gorgan"));
            jsonIds.add(new JsonId(132144,"Hamadan"));
            jsonIds.add(new JsonId(118743,"Rasht"));
            jsonIds.add(new JsonId(128226,"Kermanshah"));
            jsonIds.add(new JsonId(119505,"Qazvin"));
            jsonIds.add(new JsonId(143083,"Ardabil"));
            jsonIds.add(new JsonId(116402,"Semnan"));
            jsonIds.add(new JsonId(128234,"Kerman"));
            jsonIds.add(new JsonId(143127,"Arak"));
            jsonIds.add(new JsonId(130802,"Ilam"));
            jsonIds.add(new JsonId(128747,"Karaj"));
            jsonIds.add(new JsonId(418863,"Esfahan"));
            jsonIds.add(new JsonId(112931,"Tehran"));
            jsonIds.add(new JsonId(124665,"Mashhad"));
            jsonIds.add(new JsonId(119374,"Qeshm"));
            jsonIds.add(new JsonId(139817,"Bandar Bushehr"));
            jsonIds.add(new JsonId(139817,"Bushehr"));
            jsonIds.add(new JsonId(111453,"Zanjan"));
            jsonIds.add(new JsonId(115019,"Shiraz"));
            jsonIds.add(new JsonId(115770,"Shahr-e Kord"));
            jsonIds.add(new JsonId(140380,"Bojnurd"));
            jsonIds.add(new JsonId(1159301,"Zahedan"));
            jsonIds.add(new JsonId(113646,"Tabriz"));
            jsonIds.add(new JsonId(1159301,"Zahedan"));
            jsonIds.add(new JsonId(1159301,"Zahedan"));


            Observable<List<JsonId>> observable = Observable.just(jsonIds);

            observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .subscribe(new Consumer<List<JsonId>>() {
                        @Override
                        public void accept(List<JsonId> jsonIds) throws Exception {
                            Log.i("iii","ppp");
                           // Context context = ;
                            weatherDao dao = INSTANCE.wDao();
                            dao.insertCityId(jsonIds);
                        }
                    });
    }
    };
}


