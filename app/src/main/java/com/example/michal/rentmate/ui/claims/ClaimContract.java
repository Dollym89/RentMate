package com.example.michal.rentmate.ui.claims;

import com.example.michal.rentmate.model.pojo.Claim;

/**
 * Created by Michal on 17/04/2016.
 */
public interface ClaimContract {

  interface Callbacks {
    void onClaimSelected(Claim claim);
    void setClaimActionBar();
    void addNewClaim();
  }
}
