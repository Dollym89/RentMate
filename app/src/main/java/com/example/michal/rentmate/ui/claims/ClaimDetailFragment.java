package com.example.michal.rentmate.ui.claims;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.repositories.ClaimRepository;
import com.example.michal.rentmate.util.Constants;

import butterknife.ButterKnife;


public class ClaimDetailFragment extends Fragment {

  private Claim claim;

  public static ClaimDetailFragment newInstance() {
    return new ClaimDetailFragment();
  }

  public static ClaimDetailFragment newInstance(String crimeID) {
    Bundle arg = new Bundle();
    arg.putSerializable(Constants.ARG_CLAIM_DETAIL_ID, crimeID);
    ClaimDetailFragment claimDetailFragment = new ClaimDetailFragment();
    claimDetailFragment.setArguments(arg);
    return claimDetailFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      String claimID = (String) getArguments().getSerializable(Constants.ARG_CLAIM_DETAIL_ID);
      claim = ClaimRepository.getInstance().getClaim(claimID);
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_claim_detail, container, false);
    ButterKnife.bind(this, view);
    return view;
  }
}
