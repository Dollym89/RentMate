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
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RentMateApi {

  //GET
  @GET("/api/apartments/")
  Call<List<Apartment>> getApartments(@Header("Authorization") String token);

  @GET("/api/claims")
  Call<List<Claim>> getClaims(@Header("Authorization") String token);

  @GET("/api/users/me")
  Call<User> getUser(@Header("Authorization") String token);

  //POST
  @Headers("Content-Type:application/json")
  @POST("/auth/signin")
  Call<TokenResponce> getToken(@Body TokenRequest tokenRequest);

  @Headers("Content-Type:application/json")
  @POST("/api/users")
  Call<TokenResponce> createUser(@Body User user);

  @Headers("Content-Type:application/json")
  @POST("/api/apartments")
  Call<Apartment> createApartment(@Header("Authorization") String token,@Body Apartment apartment);

  @Headers("Content-Type:application/json")
  @POST("/api/claims")
  Call<Claim> createClaim(@Header("Authorization") String token,@Body Claim claim);
}
