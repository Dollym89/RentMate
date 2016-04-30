package com.example.michal.rentmate.util;

import android.util.Log;
import android.widget.Toast;

import com.example.michal.rentmate.model.pojo.Apartment;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.pojo.TokenRequest;
import com.example.michal.rentmate.model.pojo.TokenResponce;
import com.example.michal.rentmate.model.pojo.User;
import com.example.michal.rentmate.model.repositories.ApartmentRepository;
import com.example.michal.rentmate.model.repositories.ClaimRepository;
import com.example.michal.rentmate.model.repositories.UserRepository;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.networking.RestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLoader {


  private static final String TAG_TOKEN = "TOKEN";
  private static final String TAG_USER = "USER";

  private static RentMateApi service;
  private static List<Claim> claimList;
  private static List<Apartment> apartmentList;
  private static User user;
  private static String token;

  public static List<Apartment> loadAptData() {

    apartmentList = new ArrayList<>();
    service = RestService.getInstance();

    Call<List<Apartment>> call = service.getApartments(token);
    call.enqueue(new Callback<List<Apartment>>() {

      @Override
      public void onResponse(Call<List<Apartment>> call, Response<List<Apartment>> response) {
        Log.e("LOADING DATA", "LOADING APARTMENTS");
        List<Apartment> aptList = response.body();
        for (int i = 0; i < aptList.size(); i++) {
          apartmentList.add(aptList.get(i));
        }
      }

      @Override
      public void onFailure(Call<List<Apartment>> call, Throwable t) {
        Log.e("LOADING DATA FAILURE", "LOADING CLAIMS UNSUCCESSFUL");
      }
    });
    return apartmentList;
  }


  public static List<Claim> loadClaimData() {

    claimList = new ArrayList<>();
    service = RestService.getInstance();
    Call<List<Claim>> call = service.getClaims(token);
    call.enqueue(new Callback<List<Claim>>() {

      @Override
      public void onResponse(Call<List<Claim>> call, Response<List<Claim>> response) {
        Log.e("LOADING DATA", "LOADING CLAIMS");
        List<Claim> claims = response.body();
        for (int i = 0; i < claims.size(); i++) {
          claimList.add(claims.get(i));
        }
      }

      @Override
      public void onFailure(Call<List<Claim>> call, Throwable t) {
        Log.e("LOADING DATA FAILURE", "LOADING CLAIMS UNSUCCESSFUL");
      }
    });
    return claimList;
  }

  public static String logIn(TokenRequest request) {
    service = RestService.getInstance();
    Call<TokenResponce> call = service.getToken(request);
    call.enqueue(new Callback<TokenResponce>() {
      @Override
      public void onResponse(Call<TokenResponce> call, Response<TokenResponce> response) {
        if (response.body() != null) {
          token = response.body().getToken();
          Log.e(TAG_TOKEN, token);
          getUser(token);
        }
        else {
          token = null;
        }

      }

      @Override
      public void onFailure(Call<TokenResponce> call, Throwable t) {
        Log.e(TAG_TOKEN, "Token is not recived");

      }
    });

    return token;
  }

  public static User getUser(final String token) {
    String header = "Bearer " + token;
    service = RestService.getInstance();
    Log.e("TOKEN - HEADER", header);
    Call<User> call = service.getUser(header);
    call.enqueue(new Callback<User>() {
      @Override
      public void onResponse(Call<User> call, Response<User> response) {
        user = response.body();
        user.setToken(token);
        Log.e(TAG_USER, user.getFirstName());

        UserRepository userRepo = UserRepository.getInstance();
        userRepo.setUser(user);
        setUsersApt();
      }

      @Override
      public void onFailure(Call<User> call, Throwable t) {
        Log.e(TAG_USER, "FAILURE no users downloaded");
      }
    });
    return user;
  }

  public static void updateClaimRepository(List<Claim> claims) {
    ClaimRepository claimRepository = ClaimRepository.getInstance();
    claimRepository.getClaimList().clear();
    claimRepository.setClaimList(claims);
  }

  public static void setUsersApt() {
    ApartmentRepository aptRepo = ApartmentRepository.getInstance();
    aptRepo.setApartmentList(user.getApartments());
  }

  public static void updateApartmentRepository(List<Apartment> apartments) {
    UserRepository userRepo = UserRepository.getInstance();
    ApartmentRepository apartmentRepository = ApartmentRepository.getInstance();
    apartmentRepository.getApartmentList().clear();
    apartmentRepository.setApartmentList(apartments);
  }
}
