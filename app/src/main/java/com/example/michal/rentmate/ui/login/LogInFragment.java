package com.example.michal.rentmate.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.TokenRequest;
import com.example.michal.rentmate.model.pojo.TokenResponce;
import com.example.michal.rentmate.model.pojo.User;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.networking.RestService;
import com.example.michal.rentmate.ui.activity.RentMateActivity;
import com.example.michal.rentmate.util.ValidUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInFragment extends Fragment {

  @Bind(R.id.log_in_button) Button logInButton;
  @Bind(R.id.user_email_edit_text) EditText emailEditText;
  @Bind(R.id.password_edit_text) EditText passwordEditText;

  private RentMateApi service;
  private String token;

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

    if (!ValidUtil.isValidEmail(email)) {
      emailEditText.setError("Invalid Email");
    }
    if (!ValidUtil.isValidPassword(pass)) {
      passwordEditText.setError("Insert at least 6 characters");
    }
    else {

      TokenRequest request = new TokenRequest();
//    request.setUsername(String.valueOf(emailEditText.getText()));
//    request.setPassword(String.valueOf(password.getText()));
      request.setUsername("user");
      request.setPassword("test");
      getToken(request);
      Intent intent = RentMateActivity.newIntent(getActivity());
      startActivity(intent);
    }
  }

  public void getToken(TokenRequest request) {
    service = RestService.getInstance();
    Call<TokenResponce> call = service.getToken(request);
    call.enqueue(new Callback<TokenResponce>() {
      @Override
      public void onResponse(Call<TokenResponce> call, Response<TokenResponce> response) {
        token = response.body().getToken();
        Log.e("TOKEN", token);
        getUser();
      }

      @Override
      public void onFailure(Call<TokenResponce> call, Throwable t) {
        Log.e("TOKEN", "Token is not recived");
      }
    });
  }

  public void getUser() {
    final List<User> users = new ArrayList<>();
    String header = "Bearer " + token;
    service = RestService.getInstance();
//    Log.e("TOKEN - HEADER",header);
    Call<List<User>> call = service.logInUser(header);
    call.enqueue(new Callback<List<User>>() {
      @Override
      public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        List<User> userList = response.body();

        for (int i = 0; i < userList.size(); i++) {
          users.add(userList.get(i));
          Log.e("USERS", users.get(i).getFirstName());
        }
      }

      @Override
      public void onFailure(Call<List<User>> call, Throwable t) {
        Log.e("USERS", "FAILURE no users downloaded");
      }
    });
  }
}
