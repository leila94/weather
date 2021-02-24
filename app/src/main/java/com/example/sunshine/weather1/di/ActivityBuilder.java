package com.example.sunshine.weather1.di;

import com.example.sunshine.weather1.di.current.CurrentActivityModule;
import com.example.sunshine.weather1.di.current.CurrentViewModelModule;
import com.example.sunshine.weather1.di.main.MainActivityModule;
import com.example.sunshine.weather1.di.main.MainViewModelModule;
import com.example.sunshine.weather1.view.CurrentWeatherActivity;
import com.example.sunshine.weather1.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainViewModelModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {CurrentActivityModule.class, CurrentViewModelModule.class})
    abstract CurrentWeatherActivity bindCurrentActivity();
}
