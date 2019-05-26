package com.example.sunshine.weather1;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by Leila on 5/1/2019.
 */

public class SingleVars extends Application {

    public static MyRecyclerViewAdapter adapter1;
    private static AppDatabase db;
    public static int dbCount;


    @Override
    public void onCreate() {
        super.onCreate();

       // db = AppDatabase.getInstance(this)
    }
public static int getDbCount(){
        return dbCount;
}

public void setDbCount(int dbCount1){
    this.dbCount=dbCount1;
}
    public static MyRecyclerViewAdapter getAdapter1(){
        return adapter1;
    }

   // public static void setAdapter1(MyRecyclerViewAdapter adapter1){
     //   this.adapter1=adapter1;
    //}
}
