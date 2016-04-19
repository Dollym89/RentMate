package com.example.michal.rentmate.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.ui.activity.RentMateActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Michal on 19/04/2016.
 */
public class LogInFragment extends Fragment {

  @Bind(R.id.log_in_button)
  Button logInButton;

  public static LogInFragment newInstance() {
    return new LogInFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_login, container, false);
    ButterKnife.bind(this, view);

    logInButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = RentMateActivity.newIntent(getActivity());
        startActivity(intent);
      }
    });

    return view;
  }
}
