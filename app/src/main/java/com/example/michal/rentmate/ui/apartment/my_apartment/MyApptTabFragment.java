package com.example.michal.rentmate.ui.apartment.my_apartment;

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
import com.example.michal.rentmate.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyApptTabFragment extends Fragment {

  @Bind(R.id.apartment_tab_layout) TabLayout tabLayout;
  @Bind(R.id.apartment_tab_view_pager) ViewPager pager;

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

      return position == 0 ? MyApptListFragment.newInstance() : MyApptMapFragment.newInstance();
    }

    @Override
    public int getCount() {
      return Constants.NUMBER_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

      return position == 0 ? getString(R.string.apartment_tab_my_apt) : getString(R.string.apartment_tab_location);
    }
  }
}
