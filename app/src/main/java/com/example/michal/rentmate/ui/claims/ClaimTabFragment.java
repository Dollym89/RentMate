package com.example.michal.rentmate.ui.claims;

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
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.model.repositories.ClaimRepository;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Michal on 23/04/2016.
 */
public class ClaimTabFragment extends Fragment {

  private static final String ARG_CLAIM_ID = "claim_ID";
  private static final int NUMBER_OF_TABS = 2;

  @Bind(R.id.claim_detail_view_pager) ViewPager pager;
  @Bind(R.id.claim_detail_tab_layout) TabLayout tabLayout;

  private Claim claim;

  public static ClaimTabFragment newInstance(String claimID) {
    Bundle arg = new Bundle();
    arg.putSerializable(ARG_CLAIM_ID, claimID);
    ClaimTabFragment tabFragment = new ClaimTabFragment();
    tabFragment.setArguments(arg);
    return tabFragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String claimID = (String) getArguments().getSerializable(ARG_CLAIM_ID);
    claim = ClaimRepository.getInstance().getClaim(claimID);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_tab_claim, container, false);
    ButterKnife.bind(this, view);
    initLayout();
    return view;
  }

  private void initLayout() {
    FragmentManager manager = getFragmentManager();
    ClaimPagerAdapter adapter = new ClaimPagerAdapter(manager);
    adapter.notifyDataSetChanged();
    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
    pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
  }

  private class ClaimPagerAdapter extends FragmentStatePagerAdapter {

    public ClaimPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {

      return position == 0 ? ClaimDetailFragment.newInstance() : ClaimMessageFragment.newInstance();
    }

    @Override
    public int getCount() {
      return NUMBER_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      String title = "";
      switch (position) {
        case 0:
          title = getString(R.string.claim_tab_detail);
          break;
        case 1:
          title = getString(R.string.claim_tab_message);
          break;
      }
      return title;
    }
  }
}
