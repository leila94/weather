package com.example.sunshine.weather1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sunshine.weather1.WeatherResponse;
import com.example.sunshine.weather1.repository.Repository;

import javax.inject.Inject;

public class CurrentViewModel extends ViewModel {

    private Repository repository;
    public LiveData<WeatherResponse> weatherResponse;

    @Inject
    public CurrentViewModel(Repository repository) {
        this.repository = repository;

    }

    public void fetchWeather(int id){

    }
}
