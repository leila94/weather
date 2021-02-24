package com.example.sunshine.weather1.di.current;


import androidx.lifecycle.ViewModel;

import com.example.sunshine.weather1.di.ViewModelKey;
import com.example.sunshine.weather1.viewModel.CurrentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CurrentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentViewModel.class)
    public abstract ViewModel bindCurrentViewModel(CurrentViewModel currentViewModel);
}
