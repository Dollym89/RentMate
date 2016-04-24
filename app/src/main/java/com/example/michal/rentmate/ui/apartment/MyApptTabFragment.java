package com.example.michal.rentmate.ui.apartment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michal.rentmate.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Michal on 15/04/2016.
 */
public class MyApptTabFragment extends Fragment {

  @Bind(R.id.apartment_tab_layout)
  TabLayout tabLayout;
  @Bind(R.id.apartment_tab_view_pager)
  ViewPager pager;


  public static MyApptTabFragment newInstance() {
    return new MyApptTabFragment();
  }


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_tab_apartment, container, false);
    ButterKnife.bind(this, view);

    initLayout();

    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    initLayout();
  }


  private void initLayout() {
    FragmentManager manager = getFragmentManager();

    ApartmentPagerAdapter adapter = new ApartmentPagerAdapter(manager);
    adapter.notifyDataSetChanged();

    pager.setAdapter(adapter);

    tabLayout.setupWithViewPager(pager);

    pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
  }

  public class ApartmentPagerAdapter extends FragmentStatePagerAdapter {


    public ApartmentPagerAdapter(FragmentManager fm) {
      super(fm);

    }

    @Override
    public Fragment getItem(int position) {
      Fragment fragment = null;
      switch (position) {
        case 0:
          fragment = MyApptListFragment.newInstance();
          break;
        case 1:
          fragment = MyApptMapFragment.newInstance();
          break;

      }
      return fragment;
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

      String title = "";
      switch (position) {
        case 0:
          title = "My Apartments";
          break;
        case 1:
          title = "Location";
          break;

      }
      return title;
    }
  }


}
