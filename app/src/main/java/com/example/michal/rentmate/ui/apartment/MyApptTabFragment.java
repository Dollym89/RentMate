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


/**
 * Created by Michal on 15/04/2016.
 */
public class MyApptTabFragment extends Fragment {

  private TabLayout tabLayout;
  private ViewPager pager;


  public static MyApptTabFragment newInstance() {
    return new MyApptTabFragment();
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_tab_apartment, container, false);

    pager = (ViewPager) view.findViewById(R.id.view_pager);
    tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

    FragmentManager manager = getFragmentManager();

    PagerAdapter adapter = new PagerAdapter(manager);
    adapter.notifyDataSetChanged();

    pager.setAdapter(adapter);

    tabLayout.setupWithViewPager(pager);

    pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    return view;
  }

  @Override
  public void onResume() {
    super.onResume();


    FragmentManager manager = getFragmentManager();

    PagerAdapter adapter = new PagerAdapter(manager);
    adapter.notifyDataSetChanged();

    pager.setAdapter(adapter);

    tabLayout.setupWithViewPager(pager);

    pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

  }

  public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
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
