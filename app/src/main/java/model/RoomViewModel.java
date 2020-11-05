package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import util.RoomRepository;

public class RoomViewModel  extends AndroidViewModel {
    private RoomRepository roomRepository;
    private LiveData<List<CovidData>> allCovidData;

    public RoomViewModel(@NonNull Application application){
        super(application);

        roomRepository = new RoomRepository(application);
        allCovidData = roomRepository.getAllCovidData();
    }

    public LiveData<List<CovidData>> getAllCovidData(){
        return allCovidData;
    }

    public void insert(CovidData covidData){
        roomRepository.insert(covidData);
    }
}
