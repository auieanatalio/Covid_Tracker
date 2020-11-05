package com.auie.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import model.CovidData;
import model.RoomViewModel;
import ui.RecyclerAdapterSaved;

public class SavedCovidData extends AppCompatActivity {
    RecyclerView recyclerView;
    List<CovidData> covidDataList;
    RecyclerAdapterSaved recyclerAdapterSaved;
    RoomViewModel roomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_covid_data);

        //observes and loads saved data from room database
        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        roomViewModel.getAllCovidData().observe(this, new Observer<List<CovidData>>() {
            @Override
            public void onChanged(List<CovidData> covidData) {
                recyclerAdapterSaved.setCovidDataRoomList(covidData);
            }
        });

        //shows data and recycles view
        covidDataList= new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerAdapterSaved = new RecyclerAdapterSaved(this,covidDataList);
        recyclerView.setAdapter(recyclerAdapterSaved);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}