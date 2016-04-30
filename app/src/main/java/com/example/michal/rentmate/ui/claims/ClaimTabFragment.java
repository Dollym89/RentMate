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
import com.example.michal.rentmate.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClaimTabFragment extends Fragment {

  @Bind(R.id.claim_detail_view_pager) ViewPager pager;
  @Bind(R.id.claim_detail_tab_layout) TabLayout tabLayout;

  private Claim claim;

  public static ClaimTabFragment newInstance(String claimID) {
    Bundle arg = new Bundle();
    arg.putSerializable(Constants.ARG_CLAIM_DETAIL_ID, claimID);
    ClaimTabFragment tabFragment = new ClaimTabFragment();
    tabFragment.setArguments(arg);
    return tabFragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String claimID = (String) getArguments().getSerializable(Constants.ARG_CLAIM_DETAIL_ID);
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
      return Constants.NUMBER_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

      return position == 0 ? getString(R.string.claim_tab_detail) : getString(R.string.claim_tab_message);
    }
  }
}
