package com.example.michal.rentmate.ui.activity;

import android.graphics.Typeface;
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

import butterknife.Bind;
import butterknife.ButterKnife;


public class LogInActivity extends AppCompatActivity {

  @Bind(R.id.welcome_textview)
  TextView welcomeText;
  @Bind(R.id.login_view_pager)
  ViewPager pager;
  @Bind(R.id.tab_login_layout)
  TabLayout tabLayout;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);
    ButterKnife.bind(this);
    setFancyText(welcomeText);

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


  private void setFancyText(TextView welcomeText) {
    Typeface fancyOne = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
    welcomeText.setTypeface(fancyOne);
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
          fragment = LogInFragment.newInstance();
          break;
        case 1:
          fragment = SignUpFragment.newInstance();
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
          title = "LOGIN";
          break;
        case 1:
          title = "SIGN UP";
          break;
      }
      return title;
    }
  }


}
