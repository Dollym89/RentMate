package com.example.michal.rentmate.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.TokenResponce;
import com.example.michal.rentmate.model.pojo.User;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.networking.RestService;
import com.example.michal.rentmate.util.Constants;
import com.example.michal.rentmate.util.ValidUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends Fragment {


  @Bind(R.id.new_user_email_edit_text) EditText emailEditText;
  @Bind(R.id.new_user_password_edit_text) EditText passwordEditText;
  @Bind(R.id.new_user_switch) Switch userSwitch;
  @Bind(R.id.sign_up_button) Button signUpButton;

  private RentMateApi service;

  public static SignUpFragment newInstance() {
    return new SignUpFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_sign_up, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  //  Listener
  @OnClick(R.id.sign_up_button)
  public void onSignUpressed() {

//    final String email = emailEditText.getText().toString();
//    final String pass = passwordEditText.getText().toString();
   String email = "Dolly@gmail.com";
   String pass = "test";

    User user = setUserProperties(email, pass);
    service = RestService.getInstance();
    Call<User> call = service.createUser(user);
    call.enqueue(new Callback<User>() {
      @Override
      public void onResponse(Call<User> call, Response<User> response) {
        Log.e("NEW USER", "SUCCESS");
      }

      @Override
      public void onFailure(Call<User> call, Throwable t) {
        Log.e("NEW USER", "FAILED");
      }
    });


  }


  private User setUserProperties(String email, String pass) {
    User user = new User();
    if (userSwitch.isActivated()) {
      user.setGroupId(Constants.GROUP_ID_LANDLORD);
    } else {
      user.setGroupId(Constants.GROUP_ID_TENANT);
    }
    user.setEmail(email);
    user.setPassword(pass);
    return user;
  }

}
