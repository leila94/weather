package com.example.sunshine.weather1;


import com.example.sunshine.weather1.di.DaggerAppComponent;
import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerApplication;

public class MyApplication extends DaggerApplication {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector2;


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();

    }
}
