
package com.example.michal.rentmate.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Apartment {

    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("apartment_id")
    @Expose
    private String apartmentId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("__v")
    @Expose
    private int V;
    @SerializedName("claims")
    @Expose
    private List<Claim> claims = new ArrayList<Claim>();

    /**
     * 
     * @return
     *     The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The apartmentId
     */
    public String getApartmentId() {
        return apartmentId;
    }

    /**
     * 
     * @param apartmentId
     *     The apartment_id
     */
    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The V
     */
    public int getV() {
        return V;
    }

    /**
     * 
     * @param V
     *     The __v
     */
    public void setV(int V) {
        this.V = V;
    }

    /**
     * 
     * @return
     *     The claims
     */
    public List<Claim> getClaims() {
        return claims;
    }

    /**
     * 
     * @param claims
     *     The claims
     */
    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

}
