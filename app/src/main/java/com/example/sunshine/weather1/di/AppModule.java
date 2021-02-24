package com.example.sunshine.weather1.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.room.Room;
import com.example.sunshine.weather1.AppDatabase;
import com.example.sunshine.weather1.R;
import com.example.sunshine.weather1.WeatherService;
import com.example.sunshine.weather1.weatherDao;
import com.google.gson.stream.JsonReader;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public JsonReader reader(Context context){
        try {
            return new JsonReader(new InputStreamReader(context.getAssets().
                    open("ijCityList.json")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Provides
    @Singleton
    public AppDatabase getDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "WeatherDataBase")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    weatherDao getDao(AppDatabase database){
        return database.wDao();
    }

    @Provides
    @Singleton
    SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    OkHttpClient getClient(){
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit getRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }


    @Provides
    @Singleton
    WeatherService getApi(Retrofit retrofit){
        return retrofit.create(WeatherService.class);
    }
}
