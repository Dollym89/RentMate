package com.example.michal.rentmate.ui.apartment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.rentmate.R;


/**
 * Created by Michal on 05/04/2016.
 */
public class MyApptDetailFragment extends Fragment {







    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_appt_detail, container, false);
        return view;
    }
}
