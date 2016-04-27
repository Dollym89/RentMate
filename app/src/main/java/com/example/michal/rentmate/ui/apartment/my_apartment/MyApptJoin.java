package com.example.michal.rentmate.ui.apartment.my_apartment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.rentmate.R;

public class MyApptJoin extends Fragment {

  public static MyApptJoin newInstance() {
    return new MyApptJoin();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_my_appt_join, container, false);
    return view;
  }
}
