package com.example.michal.rentmate.ui.apartment;

/**
 * Created by Michal on 17/04/2016.
 */
public interface MyApptContract {

  interface Callbacks {
    void onApartmentSelected();
    void setApartmentActionBar();
    void addNewApartment();
  }
}
