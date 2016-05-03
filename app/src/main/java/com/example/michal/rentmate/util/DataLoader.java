package com.example.michal.rentmate.util;

import android.content.Context;
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

  public DataLoader() {
  }

  public DataLoader(Context context) {
    this.context = context.getApplicationContext();
  }

  private static final String TAG_TOKEN = "TOKEN";
  private static final String TAG_USER = "USER";

  private static RentMateApi service;
  private static List<Claim> claimList;
  private static List<Apartment> apartmentList;
  private static String token;
  private Context context;

  public static void loadAptData() {

    apartmentList = new ArrayList<>();
    service = RestService.getInstance();

    Call<List<Apartment>> call = service.getApartments(token);
    call.enqueue(new Callback<List<Apartment>>() {

      @Override
      public void onResponse(Call<List<Apartment>> call, Response<List<Apartment>> response) {
        Log.e("LOADING DATA", "LOADING APARTMENTS");
        List<Apartment> aptList = response.body();
        updateApartmentRepository(apartmentList);
      }

      @Override
      public void onFailure(Call<List<Apartment>> call, Throwable t) {
        Log.e("LOADING DATA FAILURE", "LOADING CLAIMS UNSUCCESSFUL");
      }
    });
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


  public static void updateClaimRepository(List<Claim> claims) {
    ClaimRepository claimRepository = ClaimRepository.getInstance();
    claimRepository.getClaimList().clear();
    claimRepository.setClaimList(claims);
  }

  public static void updateApartmentRepository(List<Apartment> apartments) {

    ApartmentRepository apartmentRepository = ApartmentRepository.getInstance();
    apartmentRepository.getApartmentList().clear();
    apartmentRepository.setApartmentList(apartments);
  }
}
