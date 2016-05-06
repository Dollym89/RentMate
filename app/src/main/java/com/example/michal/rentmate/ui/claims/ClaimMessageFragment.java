package com.example.michal.rentmate.ui.claims;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.pojo.User;
import com.example.michal.rentmate.model.repositories.UserRepository;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.networking.RestService;
import com.example.michal.rentmate.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClaimMessageFragment extends Fragment {

  @Bind(R.id.detail_claim_message_textview) TextView detailMessage;

  private RentMateApi service;
  private User user;


  public static ClaimMessageFragment newInstance() {
    return new ClaimMessageFragment();
  }

  public static ClaimMessageFragment newInstance(String claimID) {
    Bundle arg = new Bundle();
    arg.putSerializable(Constants.ARG_CLAIM_MESSAGE_ID, claimID);

    ClaimMessageFragment messageFragment = new ClaimMessageFragment();
    messageFragment.setArguments(arg);
    return messageFragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    user = UserRepository.getInstance().getUser();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_claim_messsage, container, false);
    ButterKnife.bind(this, view);


    return view;
  }

  private void getClaimMessage() {
    service = RestService.getInstance();

    Call<Claim> call = service.getClaim(Constants.AUTHENTICATION + user.getToken(), "CL10001");
    call.enqueue(new Callback<Claim>() {
      @Override
      public void onResponse(Call<Claim> call, Response<Claim> response) {
        String message = response.body().getMessages().get(0).getMessage();
        detailMessage.setText(message);
      }

      @Override
      public void onFailure(Call<Claim> call, Throwable t) {

      }
    });
  }

}
