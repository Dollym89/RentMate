package com.example.michal.rentmate.ui.claims;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michal.rentmate.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClaimMessageFragment extends Fragment {

  private static final String ARG_CLAIM_ID = "claim_ID_message";
  @Bind(R.id.detail_claim_message_textview)TextView detailMessage;


  public static ClaimMessageFragment newInstance(){
    return new ClaimMessageFragment();
  }

  public static ClaimMessageFragment newInstance(String claimID){
    Bundle arg = new Bundle();
    arg.putSerializable(ARG_CLAIM_ID,claimID);

    ClaimMessageFragment messageFragment = new ClaimMessageFragment();
    messageFragment.setArguments(arg);
    return messageFragment;
  }
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_claim_messsage,container,false);
    ButterKnife.bind(this,view);

    detailMessage.setText("DETAIL MESSAGE");
    return view;
  }
}
