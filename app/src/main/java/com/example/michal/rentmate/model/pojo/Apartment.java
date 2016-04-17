package com.example.michal.rentmate.model.pojo;

import java.util.UUID;

/**
 * Created by Michal on 06/04/2016.
 */
public class Apartment {

    private UUID id;
    private String address;
    private String postalCode;
    private boolean isEmpty;


    public Apartment() {
        this(UUID.randomUUID());
    }

    public Apartment(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
