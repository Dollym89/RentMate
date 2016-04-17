package com.example.michal.rentmate.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Michal on 09/04/2016.
 */
public class RestService {
    private static String url = "http://blooming-tundra-81185.herokuapp.com";

    private static UserApi service;

    private RestService() {

    }

    public static UserApi getInstance() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(UserApi.class);
            return service;
        }
        else {
            return service;
        }
    }


}
