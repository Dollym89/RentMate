package com.example.michal.rentmate.ui.claims;

import android.app.Activity;
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
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.repositories.UserRepository;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.networking.RestService;
import com.example.michal.rentmate.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClaimNew extends Fragment {

  @Bind(R.id.claim_title_edit_text) EditText titleEditText;
  @Bind(R.id.claim_detail_description) EditText descriptionEditText;
  @Bind(R.id.create_claim_button) Button saveButton;

  private RentMateApi service;
  private UserRepository userRepo;

  public static ClaimNew newInstance() {
    return new ClaimNew();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_new_claim, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  //  Listeners
  @OnClick(R.id.create_claim_button)
  public void onClaimSaved() {
    String header = setHeader();
    Claim claim = setClaimProp();
    saveClaim(header,claim);
  }

  private void saveClaim(String header,Claim claim) {

    service = RestService.getInstance();
    Call<Claim> call = service.createClaim(header,claim);
    call.enqueue(new Callback<Claim>() {
      @Override
      public void onResponse(Call<Claim> call, Response<Claim> response) {
        if (response.isSuccessful()) {
//          TODO add claim to user after backend fix
          userRepo.getUser();
          Log.e(Constants.TAG_ON_CREATED, String.valueOf(response.isSuccessful()));
          sendResult(Activity.RESULT_OK,true);
        } else {
          Log.e(Constants.TAG_ON_CREATED, "APARTMENT IS NOT CREATED");
        }
      }

      @Override
      public void onFailure(Call<Claim> call, Throwable t) {

      }
    });
  }

  private String setHeader() {
    userRepo = UserRepository.getInstance();
    String token = userRepo.getUser().getToken();
    return Constants.AUTHENTICATION + token;
  }
  private Claim setClaimProp() {
    Claim claim = new Claim();
    String title = String.valueOf(titleEditText.getText());
    String description = String.valueOf(descriptionEditText.getText());
    claim.setTitle(title);
    claim.setDescription(description);
    return claim;
  }

  private void sendResult(int resultCode, Boolean isClaimAdded) {
    if (getTargetFragment() == null) {
      return;
    }
    Intent intent = new Intent();
    intent.putExtra(Constants.EXTRA_NEW_CLAIM, isClaimAdded);
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
  }
}
