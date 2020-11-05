package util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import model.CovidData;
import network.ApiService;
import network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroRepository {
    Application application;
    public static ApiInterface apiInterface;
    private MutableLiveData<List<CovidData>> covidDataList = new MutableLiveData<>();

    private static RetroRepository retroRepository;

    public RetroRepository(Application application) {
        this.application = application;
    }

    public static RetroRepository getInstance(){
        if(retroRepository== null){
            retroRepository = new RetroRepository();
        }
        return retroRepository;
    }

    public  RetroRepository() {
        apiInterface = ApiService.getInterface();
    }

    public MutableLiveData<List<CovidData>> getCovidDataList(){
        Call<List<CovidData>> call = apiInterface.getCovidData();
        call.enqueue(new Callback<List<CovidData>>() {
            @Override
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                if(response.isSuccessful()){
                    covidDataList.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<CovidData>> call, Throwable t) {
                Log.d("Fail", "onFailure: + Fail");
            }
        });
        return covidDataList;
    }
    }

