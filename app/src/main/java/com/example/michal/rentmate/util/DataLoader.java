package com.example.michal.rentmate.util;

import android.util.Log;

import com.example.michal.rentmate.model.pojo.Apartment;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.repositories.ApartmentRepository;
import com.example.michal.rentmate.model.repositories.ClaimRepository;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.networking.RestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Michal on 21/04/2016.
 */
public class DataLoader {

  private static RentMateApi service;

  private static List<Claim> claimList;
  private static List<Apartment> apartmentList;


  public static List<Apartment> loadAptData() {

    apartmentList = new ArrayList<>();
    service = RestService.getInstance();

    Call<List<Apartment>> call = service.getApartments();

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
    Call<List<Claim>> call = service.getClaims();
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

  public static List<Claim> loadClaimSynch() throws IOException {
    claimList = new ArrayList<>();
    service = RestService.getInstance();
    Call<List<Claim>> call = service.getClaims();
    Log.e("LOADING DATA", "LOADING CLAIMS");
    claimList = call.execute().body();


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
