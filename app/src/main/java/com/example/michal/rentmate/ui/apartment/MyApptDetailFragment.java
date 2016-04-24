package com.example.michal.rentmate.ui.apartment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Apartment;
import com.example.michal.rentmate.model.repositories.ApartmentRepository;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Michal on 23/04/2016.
 */
public class MyApptDetailFragment extends Fragment {

  @Bind(R.id.apartment_detail_address_textview)
  TextView detailAddress;
  @Bind(R.id.apartment_detail_postalcode_textview)
  TextView detailPostalCode;
  @Bind(R.id.apartment_detail_access_Key_textview)
  TextView detailAccesKey;
  @Bind(R.id.apartment_detail_occupied_textview)
  TextView detailOccupied;

  private static final String ARG_APT = "apartment_id";

  private Apartment apartment;


  public static MyApptDetailFragment newInstance(String apartmentID) {
    Bundle arg = new Bundle();
    arg.putSerializable(ARG_APT, apartmentID);
    MyApptDetailFragment detailFragment = new MyApptDetailFragment();
    detailFragment.setArguments(arg);
    return detailFragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String apartmentId = (String) getArguments().getSerializable(ARG_APT);
    apartment = ApartmentRepository.getInstance().getApartment(apartmentId);



  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_my_appt_detail, container, false);
    ButterKnife.bind(this, view);
    populateUI(apartment);
    setMapFragment(apartment);
    return view;
  }


  private void setMapFragment(Apartment apartment) {
    FragmentManager manager = getChildFragmentManager();

    Fragment fragment = manager.findFragmentByTag("MAP_DETAIL_FRAGMENT");

    if (fragment == null) {
      fragment = MyApptMapDetailFragment.newInstance(apartment.getApartmentId());
    }
    manager.beginTransaction()
        .add(R.id.frag_detail_map_container, fragment, "MAP_DETAIL_FRAGMENT")
        .addToBackStack("MAP_DETAIL_FRAGMENT")
        .commit();

  }

  private void populateUI(Apartment apartment) {

    detailAddress.setText(apartment.getName());
//    detailPostalCode.setText(apartment.getPostalCode);
      detailAccesKey.setText(apartment.getApartmentId());
//    detailOccupied.setText(apartment.isOccupied);
  }

}
