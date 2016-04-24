
package com.example.michal.rentmate.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Claim {

    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("claim_id")
    @Expose
    private String claimId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("__v")
    @Expose
    private int V;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = new ArrayList<Message>();

//    /**
//     *
//     * @return
//     *     The Id
//     */
//    public String getId() {
//        return Id;
//    }
//
//    /**
//     *
//     * @param Id
//     *     The _id
//     */
//    public void setId(String Id) {
//        this.Id = Id;
//    }

    /**
     * 
     * @return
     *     The claimId
     */
    public String getClaimId() {
        return claimId;
    }

    /**
     * 
     * @param claimId
     *     The claim_id
     */
    public void setClaimId(String claimId) {
        this.claimId = claimId;
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
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
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
     *     The messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * 
     * @param messages
     *     The messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
