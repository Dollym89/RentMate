package com.example.michal.rentmate.model.repositories;

import com.example.michal.rentmate.model.pojo.Claim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 21/03/2016.
 */
public class ClaimRepository {

  private static ClaimRepository claimSingleton = null;

  private List<Claim> claimList;


  public static ClaimRepository getInstance() {
    if (claimSingleton == null) {
      claimSingleton = new ClaimRepository();
    }
    return claimSingleton;
  }


  private ClaimRepository() {
    this.claimList = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Claim claim = new Claim();
//            claim.setName("Claim #" + i);
//            claim.setStateOfClaim(getState(i));
//            claimList.add(claim);
//        }
  }

//    private Claim.State getState(int i) {
//        Claim.State state;
//        if (i % 3 == 0) {
//            state = Claim.State.Open;
//        }
//        else if (i % 3 == 2)
//            state = Claim.State.Answer;
//        else
//            state = Claim.State.Closed;
//
//        return state;
//    }

  public List<Claim> getClaimList() {
    return claimList;
  }

  public void setClaimList(List<Claim> claimList) {
    this.claimList = claimList;
  }

  public Claim getClaim(String id) {
    for (Claim claim : claimList) {
      if (claim.getClaimId().equals(id)) {
        return claim;
      }
    }
    return null;
  }
}
