
package com.example.michal.rentmate.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Apartment {

  @SerializedName("_id")
  private String Id;
  @SerializedName("apartment_id")
  private String apartmentId;
  @SerializedName("created_at")
  private String createdAt;
  @SerializedName("updated_at")
  private String updatedAt; // plus we want time when it happend user will be able to choose date
  @SerializedName("name")
  private String name; // do we need name of the appt, ist not a pet :D
  @SerializedName("address")
  private String address; // address is good plus we need another atribute POSTAL CODE
  @SerializedName("owner")
  private Owner owner; // do we need owner? or just a user with two possible values(Landlord/Tenant)
  @SerializedName("__v")
  private int V;
  @SerializedName("claims")

  private List<Claim> claims = new ArrayList<Claim>();

  public String getApartmentId() {
    return apartmentId;
  }

  public void setApartmentId(String apartmentId) {
    this.apartmentId = apartmentId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public int getV() {
    return V;
  }

  public void setV(int V) {
    this.V = V;
  }

  public List<Claim> getClaims() {
    return claims;
  }

  public void setClaims(List<Claim> claims) {
    this.claims = claims;
  }

}
