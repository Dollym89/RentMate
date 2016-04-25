package com.example.michal.rentmate.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.ui.login.LogInFragment;
import com.example.michal.rentmate.ui.login.SignUpFragment;
import com.example.michal.rentmate.util.FontSetter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LogInActivity extends AppCompatActivity {

  private static final int NUMBER_OF_TABS = 2;

  @Bind(R.id.welcome_textview) TextView welcomeText;
  @Bind(R.id.login_view_pager) ViewPager pager;
  @Bind(R.id.tab_login_layout) TabLayout tabLayout;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);
    ButterKnife.bind(this);
    FontSetter.setFancyText(getApplicationContext(),getString(R.string.font_roboto_thin),welcomeText);
//    DataLoader.loadAptData();
//    DataLoader.loadClaimData();
    updateUI();
  }

  private void updateUI() {
    FragmentManager manager = getSupportFragmentManager();
    PagerAdapter adapter = new PagerAdapter(manager);
    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
    pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    pager.setCurrentItem(0);
    tabLayout.setupWithViewPager(pager);
  }

  public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {

      return position == 0 ? LogInFragment.newInstance() : SignUpFragment.newInstance();
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
          title = getString(R.string.login_tab_login);
          break;
        case 1:
          title = getString(R.string.login_tab_sign_up);
          break;
      }
      return title;
    }
  }
}
