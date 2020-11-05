package util;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import data.CovidDao;
import data.CovidRoomDatabase;
import model.CovidData;

public class RoomRepository {
    CovidDao covidDao;
   LiveData<List<CovidData>> allCovidData;
   CovidRoomDatabase covidRoomDatabase;

    public RoomRepository(Application application){
        CovidRoomDatabase db = CovidRoomDatabase.getDatabase(application);
        covidDao = db.covidDao();
        allCovidData = covidDao.getAllCovidData();
    }

    public LiveData<List<CovidData>> getAllCovidData(){
        return allCovidData;
    }

    public void insert(final CovidData covidData) {
        CovidRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                covidRoomDatabase.covidDao().insert(covidData);
            }
        });
    }
}
