//package com.example.michal.navigationdrawer.networking;
//
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
///**
// * Created by Michal on 09/04/2016.
// */
//public class RestService {
//    private static String url = "http://api.openweathermap.org";
//
//    private static WeatherApi service;
//
//    private RestService() {
//
//    }
//
//    public static WeatherApi getInstance() {
//        if (service == null) {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            service = retrofit.create(WeatherApi.class);
//            return service;
//        }
//        else {
//            return service;
//        }
//    }
//
//
//}
