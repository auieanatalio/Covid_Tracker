package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static String BASE_URL = "https://corona.lmao.ninja/";
     public static final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            public static ApiInterface getInterface () {
                return retrofit.create(ApiInterface.class);
            }
        }
