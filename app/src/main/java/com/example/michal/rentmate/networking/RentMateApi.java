package com.example.michal.rentmate.networking;

import com.example.michal.rentmate.model.pojo.Apartment;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.pojo.TokenRequest;
import com.example.michal.rentmate.model.pojo.TokenResponce;
import com.example.michal.rentmate.model.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Michal on 17/04/2016.
 */

public interface RentMateApi {

//////////////////////////////GET
  @GET("/api/apartments/")
  Call<List<Apartment>> getApartments();

  @GET("/api/claims")
  Call<List<Claim>> getClaims();

  ////////////////////////////POST

  @POST("auth/signin")
  Call<TokenResponce> getToken(@Body TokenRequest tokenRequest);

  @GET("api/users")
  Call<List<User>> logInUser(@Header("Authorization" )String token);

  @POST("users/new")
  Call<User> createUser(@Body User user);

}
