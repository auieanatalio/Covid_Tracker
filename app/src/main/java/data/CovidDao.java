package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

import model.CovidData;

@Dao
public interface CovidDao {
    @Insert
    long insert(CovidData covidData);

    @Query("DELETE FROM covid_data_table")
    void deleteAll();

    @Query("DELETE FROM covid_data_table WHERE id = :id")
    int delete(int id);

    @Update
    int update(CovidData covidData);

    @Query("SELECT * FROM covid_data_table ORDER BY id DESC")
    LiveData<List<CovidData>> getAllCovidData();

    }
