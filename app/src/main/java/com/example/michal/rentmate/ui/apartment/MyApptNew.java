package com.example.michal.rentmate.ui.apartment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.rentmate.R;

/**
 * Created by Michal on 19/04/2016.
 */


public class MyApptNew extends Fragment {


  public static MyApptNew newInstance(){
    return new MyApptNew();
  }
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_new_apartment, container, false);
    return view;
  }
}