package com.example.michal.rentmate.networking;

import com.example.michal.rentmate.model.pojo.Apartment;
import com.example.michal.rentmate.model.pojo.Claim;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Michal on 17/04/2016.
 */

public interface RentMateApi {


  @GET("/api/apartments/")
  Call<List<Apartment>> getApartments();

  @GET("/api/claims")
  Call<List<Claim>> getClaims();

//  @POST("auth/signin")
//  Call<>

}
