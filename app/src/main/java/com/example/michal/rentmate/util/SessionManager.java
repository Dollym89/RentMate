package com.example.michal.rentmate.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Michal on 10/04/2016.
 */
public class SessionManager {


    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("key_name", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }


    public String getPreferences(Context context, String key) {

        SharedPreferences preferences = context.getSharedPreferences("key_name", Context.MODE_PRIVATE);
        String possition = preferences.getString(key, "");
        return possition;
    }
}

