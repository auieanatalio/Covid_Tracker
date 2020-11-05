package com.auie.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import model.CovidData;
import model.RetroViewModel;
import network.ApiService;
import network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.RecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<CovidData> covidDataList;
    RecyclerAdapter recyclerAdapter;
    RetroViewModel retroViewModel;
    Button viewSavedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temporary recyclerview
        covidDataList = new ArrayList();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerAdapter = new RecyclerAdapter(this, covidDataList);
        recyclerView.setAdapter(recyclerAdapter);

        //observes and loads data from web
        retroViewModel = new ViewModelProvider(this).get(RetroViewModel.class);
        retroViewModel.getRetroRepository().observe(this, new Observer<List<CovidData>>() {
            @Override
            public void onChanged(List<CovidData> covidData) {
                recyclerAdapter.setCovidData(covidData);
            }
        });

        //shows data and recycles the view
        setRecyclerViewData();

        //starts Activity that contains saved data
        viewSavedData = findViewById(R.id.view_room);
        viewSavedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SavedCovidData.class);
                startActivity(intent);
            }
        });


        }

        public void setRecyclerViewData() {
        recyclerAdapter = new RecyclerAdapter(this, covidDataList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}