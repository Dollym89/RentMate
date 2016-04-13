package com.example.michal.navigationdrawer.ui.apartment.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.navigationdrawer.R;

/**
 * Created by Michal on 05/04/2016.
 */
public class MyApptDetailFragment extends Fragment {

    private Callbacks callbacks;

    public interface Callbacks {
        void onApptDetailSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_appt_detail, container, false);
        return view;
    }
}
