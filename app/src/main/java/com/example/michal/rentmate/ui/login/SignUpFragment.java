package com.example.michal.rentmate.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.util.ValidUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignUpFragment extends Fragment {

  public static SignUpFragment newInstance() {
    return new SignUpFragment();
  }

  @Bind(R.id.user_email_edit_text) EditText emailEditText;
  @Bind(R.id.password_edit_text) EditText passwordEditText;
  @Bind(R.id.sign_up_button) Button signUpButton;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_sign_up, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  //  Listener
  @OnClick(R.id.sign_up_button)
  public void onLogInPressed() {

    final String email = emailEditText.getText().toString();
    final String pass = passwordEditText.getText().toString();
    if (!ValidUtil.isValidEmail(email)) {
      emailEditText.setError("Invalid Email");
    }
    if (!ValidUtil.isValidPassword(pass)) {
      passwordEditText.setError("Insert at least 6 characters");
    }
    else {

      Toast.makeText(getActivity(), "YOU HAVE NEW ACCOUNT", Toast.LENGTH_SHORT).show();
    }
  }
}
