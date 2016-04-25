package com.example.michal.rentmate.ui.apartment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Apartment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Michal on 19/04/2016.
 */


public class MyApptNew extends Fragment {

  private static final int REQUEST_ADDRESS = 0;
  private static final String DIALOG_ADDRESS = "apartment_address";
  @Bind(R.id.new_apt_check_location_button) Button checkLocation;
  @Bind(R.id.new_apt_address_edit_text) EditText aptAddress;

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

  @OnClick(R.id.new_apt_check_location_button)
  public void onChecAptLocation() {
    openMapDialog();
  }

  private Address getAddress(EditText aptAddress) {
    Geocoder geocoder = new Geocoder(getActivity());
    Address address = null;

    String adrs = String.valueOf(aptAddress.getText());
    List<Address> addressList = null;
    try {
      addressList = geocoder.getFromLocationName(adrs, 1);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (addressList != null) {
      address = addressList.get(0);
      position = new LatLng(address.getLatitude(), address.getLongitude());
      MarkerOptions marker = new MarkerOptions().position(position);
      map.addMarker(marker);
    }
    return address;

  }

  private LatLng getlatLng(Address address) {
    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
    return latLng;
  }

  private void drawMark() {
    MarkerOptions mo = new MarkerOptions();
    mo.position(getlatLng(getAddress(aptAddress)));
    map.addMarker(mo);
  }

  private void openMapDialog() {
    FragmentManager manager = getFragmentManager();
    MyApptNewCheckAddress dialog = MyApptNewCheckAddress.newInstance();
    dialog.setTargetFragment(MyApptNew.this, REQUEST_ADDRESS);
    dialog.show(manager, DIALOG_ADDRESS);
  }
}
