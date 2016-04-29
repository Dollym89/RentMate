package com.example.michal.rentmate.ui.apartment.new_apartments;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Apartment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyApptNew extends Fragment {

  private static final int REQUEST_ADDRESS = 0;
  private static final String DIALOG_ADDRESS = "apartment_address";


  @Bind(R.id.new_apt_check_location_button) Button checkLocationButton;
  @Bind(R.id.new_apt_save_button) Button saveButton;

  @Bind(R.id.new_apt_country_edit_text) EditText countryEditText;
  @Bind(R.id.new_apt_city_edit_text) EditText cityEditText;
  @Bind(R.id.new_apt_street_edit_text) EditText streetEditText;
  @Bind(R.id.new_apt_zip_edit_text) EditText zipEditText;

  private GoogleMap map;
  private Apartment apartment;
  private LatLng position;

  public static MyApptNew newInstance() {
    return new MyApptNew();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_new_apartment, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == Activity.RESULT_OK) {
      switch (requestCode) {
        case REQUEST_ADDRESS:
          boolean isAddressCorrect = (boolean) data.getSerializableExtra(MyApptNewDialog.EXTRA_ADDRESS);
          saveButton.setEnabled(isAddressCorrect);
      }
    }
  }

  //  Listeners
  @OnClick(R.id.new_apt_check_location_button)
  public void onCheckAptLocation() {
    if (getFullAddress().toCharArray().length > 10 && getAddress(getFullAddress()) != null) {
      getPosition(getAddress(getFullAddress()));
      openMapDialog();
    } else {
      Toast.makeText(getActivity(), "Fill correct address", Toast.LENGTH_LONG).show();
    }
  }

  @OnClick(R.id.new_apt_save_button)
  public void onApartmentSaved() {
    Toast.makeText(getActivity(), "HA you just saved apt", Toast.LENGTH_LONG).show();
  }

  private Address getAddress(String longAddress) {
    Geocoder geocoder = new Geocoder(getActivity());
    Address address = null;

    String adrs = String.valueOf(longAddress);
    List<Address> addressList = null;
    try {
      addressList = geocoder.getFromLocationName(adrs, 1);
    } catch (IOException e) {
      e.printStackTrace();
      Toast.makeText(getActivity(), "Fill correct address", Toast.LENGTH_SHORT).show();
    }
    if (addressList != null && addressList.size() != 0) {
      address = addressList.get(0);
    } else {
      Toast.makeText(getActivity(), "We can not find your location", Toast.LENGTH_SHORT).show();
    }
    return address;
  }

  private String getFullAddress() {
    String longAddress;
    String country = String.valueOf(countryEditText.getText());
    if (TextUtils.isEmpty(country)) {
      countryEditText.setError("Country must be specified");
    }
    String city = String.valueOf(cityEditText.getText());
    if (TextUtils.isEmpty(city)) {
      cityEditText.setError("City must be specified");
    }
    String street = String.valueOf(streetEditText.getText());
    if (TextUtils.isEmpty(street)) {
      streetEditText.setError("Street must be specified");
    }
    String zipCode = String.valueOf(zipEditText.getText());
    if (TextUtils.isEmpty(zipCode)) {
      zipEditText.setError("ZIP code must be specified");
    }
    longAddress = street + "," + zipCode + city + "," + country;
    return longAddress;
  }

  private LatLng getPosition(Address address) {
    return position = new LatLng(address.getLatitude(), address.getLongitude());
  }

  private LatLng getlatLng(Address address) {
    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
    return latLng;
  }

  private void openMapDialog() {
    FragmentManager manager = getFragmentManager();
    MyApptNewDialog dialog = MyApptNewDialog.newInstance(position);
    dialog.setTargetFragment(MyApptNew.this, REQUEST_ADDRESS);
    dialog.show(manager, DIALOG_ADDRESS);
  }


}
