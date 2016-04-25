package com.example.michal.rentmate.ui.apartment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.rentmate.R;
import com.google.android.gms.maps.SupportMapFragment;


public class MyApptNewCheckAddress extends DialogFragment {

  public static MyApptNewCheckAddress newInstance() {
    return new MyApptNewCheckAddress();
  }

  private SupportMapFragment fragment;

  public MyApptNewCheckAddress() {
    fragment = new SupportMapFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_map_dialog, container, false);
    getDialog().setTitle("");
    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
    transaction.add(R.id.mapView, fragment).commit();
    return view;
  }
//  private void initFragment();

  public SupportMapFragment getFragment() {
    return fragment;
  }
}
