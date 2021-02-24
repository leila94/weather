package com.example.sunshine.weather1.di.main;


import androidx.lifecycle.ViewModel;

import com.example.sunshine.weather1.di.ViewModelKey;
import com.example.sunshine.weather1.viewModel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindCurrentViewModel(MainViewModel currentViewModel);
}
