package com.example.michal.rentmate.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Apartment;
import com.example.michal.rentmate.model.pojo.Claim;
import com.example.michal.rentmate.ui.apartment.MyApptContract;
import com.example.michal.rentmate.ui.apartment.MyApptDetailFragment;
import com.example.michal.rentmate.ui.apartment.MyApptNew;
import com.example.michal.rentmate.ui.apartment.MyApptTabFragment;
import com.example.michal.rentmate.ui.claims.ClaimContract;
import com.example.michal.rentmate.ui.claims.ClaimListFragment;
import com.example.michal.rentmate.ui.claims.ClaimNew;
import com.example.michal.rentmate.ui.claims.ClaimTabFragment;
import com.example.michal.rentmate.ui.homescreen.HomeScreenContract;
import com.example.michal.rentmate.ui.homescreen.NoticeFragment;
import com.example.michal.rentmate.ui.profile.ProfileFragment;
import com.example.michal.rentmate.ui.settings.Settings;
import com.example.michal.rentmate.util.DataLoader;
import com.example.michal.rentmate.util.FragTag;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RentMateActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    ClaimContract.Callbacks,
    MyApptContract.Callbacks,
    HomeScreenContract.Callbacks {

  @Bind(R.id.toolbar)
  Toolbar toolbar;
  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;

  private List<Apartment> apartments;
  private List<Claim> claims;


  public static Intent newIntent(Context packageContext) {
    Intent intent = new Intent(packageContext, RentMateActivity.class);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setToolbar();
//        new DataFetcher().execute();
//    TODO decide what to use Asynk or Retrofit asynk
    loadData();
    setFirstFragment();
    setDrawer();
  }

  private void setToolbar() {
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  private void loadData() {
    apartments = DataLoader.loadAptData();
    DataLoader.updateApartmentRepository(apartments);

    claims = DataLoader.loadClaimData();
    DataLoader.updateClaimRepository(claims);
  }

  private void setDrawer() {
    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  public void setFirstFragment() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag(FragTag.HOME_NOTICE_FRAG);

    if (fragment == null) {
      fragment = NoticeFragment.newInstance();
    }
    fm.beginTransaction()
        .add(R.id.fragment_container, fragment, FragTag.HOME_NOTICE_FRAG)
        .addToBackStack(FragTag.HOME_NOTICE_FRAG)
        .commit();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  public void onBackPressed() {
    Log.e("BACK PRESSED", "onBackPressed INVOKED");
    isDrawerEnable(true);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    }
    else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return true;
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {

    int id = item.getItemId();
    Fragment fragment = null;
    String TAG = "";

    switch (id) {
      case R.id.nav_home:
        fragment = NoticeFragment.newInstance();
        TAG = FragTag.HOME_NOTICE_FRAG;
        break;
      case R.id.nav_my_appartments:
        fragment = MyApptTabFragment.newInstance();
        TAG = FragTag.APARTMENT_TAB_FRAG;
        break;
      case R.id.nav_claims:
        fragment = ClaimListFragment.newInstance();
        TAG = FragTag.CLAIM_LIST_FRAG;
        break;
      case R.id.nav_profile:
        fragment = ProfileFragment.newInstance();
        TAG = FragTag.PROFILE_FRAG;
        break;
      case R.id.nav_settings:
        fragment = Settings.newInstance();
        TAG = FragTag.SETTINGS_FRAG;
        break;
    }

    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction()
        .addToBackStack(TAG)
        .replace(R.id.fragment_container, fragment, TAG)
        .commit();

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  /**
   * ClaimContract interface implementation
   */
  @Override
  public void onClaimSelected(Claim claim) {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag(FragTag.CLAIM_TAB_FRAG);
    if (fragment == null) {
      fragment = ClaimTabFragment.newInstance(claim.getClaimId());
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack(FragTag.CLAIM_TAB_FRAG)
        .replace(R.id.fragment_container, fragment, FragTag.CLAIM_TAB_FRAG)
        .commit();
  }

  @Override
  public void setClaimActionBar() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(getString(R.string.claim_action_bar));
    }
  }

  @Override
  public void addNewClaim() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag(FragTag.CLAIM_NEW_FRAG);
    if (fragment == null) {
      fragment = ClaimNew.newInstance();
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack(FragTag.CLAIM_NEW_FRAG)
        .replace(R.id.fragment_container, fragment, FragTag.CLAIM_NEW_FRAG)
        .commit();
  }

  /**
   * MyApptContract interface implementation
   */
  @Override
  public void onApartmentSelected(Apartment apartment) {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag(FragTag.APARTMENT_DETAIL_FRAG);
    if (fragment == null) {
      fragment = MyApptDetailFragment.newInstance(apartment.getApartmentId());
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack(FragTag.APARTMENT_DETAIL_FRAG)
        .replace(R.id.fragment_container, fragment, FragTag.APARTMENT_DETAIL_FRAG)
        .commit();
  }

  @Override
  public void setApartmentActionBar() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(getString(R.string.nav_my_apartments));
    }
  }

  @Override
  public void addNewApartment() {

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag(FragTag.APARTMENT_NEW_FRAG);
    if (fragment == null) {
      fragment = MyApptNew.newInstance();
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack(FragTag.APARTMENT_NEW_FRAG)
        .replace(R.id.fragment_container, fragment, FragTag.APARTMENT_NEW_FRAG)
        .commit();
  }

  /**
   * HomeScreenContract interface implementation
   */
  @Override
  public void openClaimList() {
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentByTag(FragTag.CLAIM_LIST_FRAG);

    if (fragment == null) {
      fragment = ClaimListFragment.newInstance();
    }

    manager.beginTransaction()
        .addToBackStack(FragTag.CLAIM_LIST_FRAG)
        .replace(R.id.fragment_container, fragment, FragTag.CLAIM_LIST_FRAG)
        .commit();
  }

  @Override
  public void openApartmentList() {
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentByTag(FragTag.APARTMENT_TAB_FRAG);

    if (fragment == null) {
      fragment = MyApptTabFragment.newInstance();
    }

    manager.beginTransaction()
        .addToBackStack(FragTag.APARTMENT_TAB_FRAG)
        .replace(R.id.fragment_container, fragment, FragTag.APARTMENT_TAB_FRAG)
        .commit();
  }

  /**
   * Disable/enable hamburger menu, also disable/enable left slide to show menu.
   */
  public void isDrawerEnable(boolean isEnabled) {
    if (isEnabled) {
      drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
      toggle.setDrawerIndicatorEnabled(true);
      toggle.syncState();
    }
    else {
      drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
      toggle.setDrawerIndicatorEnabled(false);
      toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          onBackPressed();
        }
      });
      toggle.syncState();
    }
  }

  private class DataFetcher extends AsyncTask<Void, Void, List<Claim>> {

    @Override
    protected List<Claim> doInBackground(Void... params) {
      Log.e("BACKGROUND ", "doInBackground is running");
      List<Claim> claimList = null;
      try {
        claimList = DataLoader.loadClaimSynch();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return claimList;
    }

    @Override
    protected void onPostExecute(List<Claim> claimList) {
      Log.e("BACKGROUND ", "onPostExecuted is running");
      DataLoader.updateClaimRepository(claimList);
      setFirstFragment();
    }
  }
}
