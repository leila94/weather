package com.example.sunshine.weather1.di;



import androidx.lifecycle.ViewModelProvider;

import com.example.sunshine.weather1.viewModel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindVieModelFactory(ViewModelFactory factory);
}
