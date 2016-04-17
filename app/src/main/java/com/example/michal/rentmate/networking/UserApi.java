package com.example.michal.rentmate.networking;

import com.example.michal.rentmate.model.pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Michal on 17/04/2016.
 */

  public interface UserApi {

    @GET("/api/users")
    Call<User> getUsers();

}
