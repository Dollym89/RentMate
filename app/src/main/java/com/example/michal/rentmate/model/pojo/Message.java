
package com.example.michal.rentmate.model.pojo;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Message {

  @SerializedName("msg")
  private String msg;
  @SerializedName("posted_at")
  private String postedAt;
  @SerializedName("_id")
  private String Id;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getPostedAt() {
    return postedAt;
  }

  public void setPostedAt(String postedAt) {
    this.postedAt = postedAt;
  }

  public String getId() {
    return Id;
  }

  public void setId(String Id) {
    this.Id = Id;
  }

}
