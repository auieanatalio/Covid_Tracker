package network;


import java.util.List;

import model.CovidData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("v2/continents?yesterday=true&sort")
    Call<List<CovidData>> getCovidData();
}
