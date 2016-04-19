package com.example.michal.rentmate.ui.profile;

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
public class Profile extends Fragment {

  public static Profile newInstance() {
    return new Profile();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_profile, container, false);


    return view;
  }
}
