package com.example.michal.navigationdrawer.model.repositories;

import android.content.Context;

import com.example.michal.navigationdrawer.model.Claim;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Michal on 21/03/2016.
 */
public class ClaimRepository {

    private static ClaimRepository claimSingleton = null;
    private Context context;
    private List<Claim> claimList;


    public static ClaimRepository getInstance(Context context) {
        if (claimSingleton == null) {
            claimSingleton = new ClaimRepository(context);
        }
        return claimSingleton;
    }


    private ClaimRepository(Context context) {
        this.claimList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Claim claim = new Claim();
            claim.setName("Claim #" + i);
            claim.setStateOfClaim(getState(i));
            claimList.add(claim);
        }
    }

    private Claim.State getState(int i) {
        Claim.State state;
        if (i % 3 == 0) {
            state = Claim.State.Open;
        }
        else if (i % 3 == 2)
            state = Claim.State.Answer;
        else
            state = Claim.State.Closed;

        return state;
    }

    public List<Claim> getClaimList() {
        return claimList;
    }

    public Claim getClaim(UUID id) {
        for (Claim claim : claimList) {
            if (claim.getId().equals(id)) {
                return claim;
            }
        }
        return null;
    }
}
