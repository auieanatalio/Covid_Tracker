package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import util.RetroRepository;

public class RetroViewModel extends AndroidViewModel {
    private RetroRepository repository;
    private MutableLiveData<List<CovidData>> covidDataList = new MutableLiveData<>();

    public RetroViewModel(@NonNull Application application) {
        super(application);
        repository = new RetroRepository(application);

    }

    public MutableLiveData<List<CovidData>>  getRetroRepository(){
        repository.getInstance();
        covidDataList = repository.getCovidDataList();
        return covidDataList;
    }





}
