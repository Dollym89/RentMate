
package com.example.michal.rentmate.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Message {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("posted_at")
    @Expose
    private String postedAt;
    @SerializedName("_id")
    @Expose
    private String Id;

    /**
     * 
     * @return
     *     The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return
     *     The postedAt
     */
    public String getPostedAt() {
        return postedAt;
    }

    /**
     * 
     * @param postedAt
     *     The posted_at
     */
    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

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

}
