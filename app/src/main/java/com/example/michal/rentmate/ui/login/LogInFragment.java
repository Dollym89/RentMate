package com.example.michal.rentmate.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.TokenRequest;
import com.example.michal.rentmate.model.pojo.User;
import com.example.michal.rentmate.model.repositories.UserRepository;
import com.example.michal.rentmate.ui.activity.RentMateActivity;
import com.example.michal.rentmate.util.DataLoader;
import com.example.michal.rentmate.util.ValidUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInFragment extends Fragment {

  @Bind(R.id.log_in_button) Button logInButton;
  @Bind(R.id.user_email_edit_text) EditText emailEditText;
  @Bind(R.id.password_edit_text) EditText passwordEditText;

  private String token;
  private User user;

  public static LogInFragment newInstance() {
    return new LogInFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_login, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  //  Listener
  @OnClick(R.id.log_in_button)
  public void onLogInPressed() {

    final String email = emailEditText.getText().toString();
    final String pass = passwordEditText.getText().toString();

    if (!ValidUtil.isValidEmail(email) || !ValidUtil.isValidPassword(pass)) {
      if (!ValidUtil.isValidEmail(email)) {
        emailEditText.setError("Invalid Email");
      }
      if (!ValidUtil.isValidPassword(pass)) {
        passwordEditText.setError("Insert at least 4 characters");
      }
    } else {
      TokenRequest request = createTokenRequest();
      DataLoader.logIn(request);

      if (DataLoader.logIn(request) == null) {
        Toast.makeText(getContext(), "Wrong password or email", Toast.LENGTH_LONG).show();
      } else {

//      TODO set to sleep?
        Intent intent = RentMateActivity.newIntent(getActivity());
        startActivity(intent);
      }
    }
  }


  public TokenRequest createTokenRequest() {
    TokenRequest request = new TokenRequest();
    request.setEmail(String.valueOf(emailEditText.getText()));
    request.setPassword(String.valueOf(passwordEditText.getText()));
//    request.setEmail("admin@gmail.com");
//    request.setPassword("test");
    return request;
  }


}
