package com.example.michal.navigationdrawer.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Michal on 21/03/2016.
 */
public class Claim {

    public enum State {Answer, Open, Closed}




    private UUID id;
    private String name;
    private Date dateOfClaim;
    private boolean claimSolved;
    private State stateOfClaim;
    private int colour;




    public Claim() {
        this(UUID.randomUUID());
    }

    public Claim(UUID id) {
        this.id = id;
        this.dateOfClaim = new Date();
        this.stateOfClaim = State.Answer;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfClaim() {
        return dateOfClaim;
    }

    public void setDateOfClaim(Date dateOfClaim) {
        this.dateOfClaim = dateOfClaim;
    }

    public boolean isClaimSolved() {
        return claimSolved;
    }

    public void setClaimSolved(boolean claimSolved) {
        this.claimSolved = claimSolved;
    }

    public State getStateOfClaim() {
        return stateOfClaim;
    }

    public void setStateOfClaim(State stateOfClaim) {
        this.stateOfClaim = stateOfClaim;
    }
}
