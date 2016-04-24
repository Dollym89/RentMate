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
  //  private UserRepository userRepo;
//  private ClaimRepository claimRepo;
//  private ApartmentRepository aptRepo;
//  private RentMateApi service;
//  private List<Claim> claims;
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
    //    new DataFetcher().execute();
    loadData();
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
    Fragment fragment = fm.findFragmentByTag("LIST_CLAIM");

    if (fragment == null) {

      fragment = NoticeFragment.newInstance();
    }

    fm.beginTransaction()
        .add(R.id.fragment_container, fragment, "LIST_CLAIM")
        .addToBackStack("CLAIM_LIST_FRAGMENT")
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
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    Fragment fragment = null;
    String TAG = "";

    switch (id) {
      case R.id.nav_home:
        fragment = NoticeFragment.newInstance();
        TAG = "HOME_NOTICE_FRAGMENT";
        break;
      case R.id.nav_my_appartments:
        fragment = MyApptTabFragment.newInstance();
        TAG = "APARTMENT_TAB_FRAGMENT";
        break;
      case R.id.nav_claims:

        fragment = ClaimListFragment.newInstance();
        TAG = "CLAIM_LIST_FRAGMENT";
        break;
      case R.id.nav_profile:
        fragment = ProfileFragment.newInstance();
        TAG = "PROFILE";
        break;
      case R.id.nav_settings:
        fragment = Settings.newInstance();
        TAG = "SETTINGS";
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
    Fragment fragment = fm.findFragmentByTag("CLAIM_TAB");
    if (fragment == null) {
      fragment = ClaimTabFragment.newInstance(claim.getClaimId());
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack("CLAIM_TAB")
        .replace(R.id.fragment_container, fragment, "CLAIM_TAB")
        .commit();
  }

  @Override
  public void setClaimActionBar() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Claims");

    }
  }

  @Override
  public void addNewClaim() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag("CLAIM_NEW");
    if (fragment == null) {
      fragment = ClaimNew.newInstance();
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack("CLAIM_NEW")
        .replace(R.id.fragment_container, fragment, "CLAIM_NEW")
        .commit();
  }

  /**
   * MyApptContract interface implementation
   */
  @Override
  public void onApartmentSelected(Apartment apartment) {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag("APARTMENT_DETAIL");
    if (fragment == null) {
      fragment = MyApptDetailFragment.newInstance(apartment.getApartmentId());
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack("APARTMENT_DETAIL")
        .replace(R.id.fragment_container, fragment, "APARTMENT_DETAIL")
        .commit();
  }

  @Override
  public void setApartmentActionBar() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle("My Apartments");
    }
  }

  @Override
  public void addNewApartment() {

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag("APARTMENT_NEW");
    if (fragment == null) {
      fragment = MyApptNew.newInstance();
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .addToBackStack("APARTMENT_NEW")
        .replace(R.id.fragment_container, fragment, "APARTMENT_NEW")
        .commit();
  }

  /**
   * HomeScreenContract interface implementation
   */
  @Override
  public void openClaimList() {
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentByTag("CLAIM_LIST_FRAGMENT");

    if (fragment == null) {
      fragment = ClaimListFragment.newInstance();
    }

    manager.beginTransaction()
        .addToBackStack("CLAIM_LIST_FRAGMENT")
        .replace(R.id.fragment_container, fragment, "CLAIM_LIST_FRAGMENT")
        .commit();
  }

  @Override
  public void openApartmentList() {
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentByTag("APARTMENT_TAB_FRAGMENT");

    if (fragment == null) {
      fragment = MyApptTabFragment.newInstance();
    }

    manager.beginTransaction()
        .addToBackStack("APARTMENT_TAB_FRAGMENT")
        .replace(R.id.fragment_container, fragment, "APARTMENT_TAB_FRAGMENT")
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
