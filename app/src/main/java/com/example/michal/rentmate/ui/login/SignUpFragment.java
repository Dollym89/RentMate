package com.example.michal.rentmate.ui.login;

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
public class SignUpFragment extends Fragment {

  public static SignUpFragment newInstance() {
    return new SignUpFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_sign_up, container, false);




    return view;
  }
}
