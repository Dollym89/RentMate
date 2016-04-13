package com.example.michal.navigationdrawer.ui.apartment.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.navigationdrawer.R;

/**
 * Created by Michal on 05/04/2016.
 */
public class MyApptFragment extends Fragment {

    private RecyclerView recyclerView;

    private Callbacks callbacks;

    public interface Callbacks {
        void onAppartmentSelected();
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
        View view = inflater.inflate(R.layout.fragment_my_appt, container, false);
        return view;
    }

    public static MyApptFragment newInstance() {
        return new MyApptFragment();

    }

    private class ApartmentHolder extends RecyclerView.ViewHolder{

        public ApartmentHolder(View itemView) {
            super(itemView);
        }
    }


}
