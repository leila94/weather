package com.example.sunshine.weather1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sunshine.weather1.repository.Repository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public void checkDB(){
        repository.initializeDB();
    }

    public LiveData<List<String>> getCities(){
        return repository.getCities();
    }

    public void fetchWeather(int position){

    }

}
