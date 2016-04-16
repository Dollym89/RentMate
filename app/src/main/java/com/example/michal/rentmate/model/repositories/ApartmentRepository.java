package com.example.michal.rentmate.model.repositories;

import com.example.michal.rentmate.model.Apartment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 12/04/2016.
 */
public class ApartmentRepository {


  private static ApartmentRepository repository;

  private String[] addressesList = {"Spitalska 16", "Velke Chlievany 163","Topolova 23,Banovce nad Bebravou"};
  private List<Apartment> apartmentList;

  private ApartmentRepository() {
    this.apartmentList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Apartment apt = new Apartment();
      apt.setAddress(addressesList[i]);

      apt.setIsEmpty(i % 2 == 0);
      apartmentList.add(apt);
    }

  }

  public static ApartmentRepository getInstance() {
    if (repository == null) {
      repository = new ApartmentRepository();
    }
    return repository;
  }

  public List<Apartment> getApartmentList() {
    return apartmentList;
  }
}
