package com.example.michal.navigationdrawer.ui.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.navigationdrawer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TenantLandlordFragment extends Fragment {


    public TenantLandlordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenant_landlord, container, false);
    }

}