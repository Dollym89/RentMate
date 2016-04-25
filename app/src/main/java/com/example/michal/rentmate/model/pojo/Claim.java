
package com.example.michal.rentmate.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Claim {

  @SerializedName("_id")
  private String Id;
  @SerializedName("claim_id")
  private String claimId;
  @SerializedName("created_at")
  private String createdAt;
  @SerializedName("updated_at")
  private String updatedAt;
  @SerializedName("title")
  private String title;
  @SerializedName("status")
  private String status;
  @SerializedName("__v")
  private int V;
  @SerializedName("messages")
  private List<Message> messages = new ArrayList<Message>();

  public String getClaimId() {
    return claimId;
  }

  public void setClaimId(String claimId) {
    this.claimId = claimId;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getV() {
    return V;
  }

  public void setV(int V) {
    this.V = V;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

}
