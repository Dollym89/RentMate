package com.example.michal.rentmate.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.michal.rentmate.model.repositories.ApartmentRepository;
import com.example.michal.rentmate.model.repositories.ClaimRepository;
import com.example.michal.rentmate.model.repositories.UserRepository;
import com.example.michal.rentmate.networking.RentMateApi;
import com.example.michal.rentmate.networking.RestService;
import com.example.michal.rentmate.ui.apartment.MyApptContract;
import com.example.michal.rentmate.ui.apartment.MyApptDetailFragment;
import com.example.michal.rentmate.ui.apartment.MyApptNew;
import com.example.michal.rentmate.ui.apartment.MyApptTabFragment;
import com.example.michal.rentmate.ui.claims.ClaimDetailFragment;
import com.example.michal.rentmate.ui.claims.ClaimContract;
import com.example.michal.rentmate.ui.claims.ClaimListFragment;
import com.example.michal.rentmate.ui.claims.ClaimNew;
import com.example.michal.rentmate.ui.profile.Profile;
import com.example.michal.rentmate.ui.settings.Settings;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentMateActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    ClaimContract.Callbacks,
    MyApptContract.Callbacks {

  @Bind(R.id.toolbar)
  Toolbar toolbar;
  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;
  private UserRepository userRepo;
  private RentMateApi service;




  public static Intent newIntent(Context packageContext){
    Intent intent = new Intent(packageContext,RentMateActivity.class);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    loadAptData();
    loadClaimData();

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        setFirstFragment();
      }
    }, 1000);


    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);


  }

  @Override
  protected void onResume() {
    super.onResume();

  }

  private void loadUserData() {

  }

  private void loadAptData() {
    ApartmentRepository aptRepo = ApartmentRepository.getInstance();
    final List<Apartment> apartmentList = aptRepo.getApartmentList();

    service = RestService.getInstance();

    Call<List<Apartment>> call = service.getApartments();

    call.enqueue(new Callback<List<Apartment>>() {
      @Override
      public void onResponse(Call<List<Apartment>> call, Response<List<Apartment>> response) {
        List<Apartment> apartments = response.body();
        Log.e("LOADING DATA", "LOADING APARTMENTS");
        for (int i = 0; i < apartments.size(); i++) {
          apartmentList.add(apartments.get(i));
        }
      }

      @Override
      public void onFailure(Call<List<Apartment>> call, Throwable t) {
        Log.e("LOADING DATA FAILURE", "LOADING CLAIMS UNSUCCESSFUL");
      }
    });

  }

  private void loadClaimData() {
    ClaimRepository claimRepo = ClaimRepository.getInstance();
    final List<Claim> claimList = claimRepo.getClaimList();

    service = RestService.getInstance();
    Call<List<Claim>> call = service.getClaims();

    call.enqueue(new Callback<List<Claim>>() {
      @Override
      public void onResponse(Call<List<Claim>> call, Response<List<Claim>> response) {
        List<Claim> claims = response.body();
        Log.e("LOADING DATA", "LOADING CLAIMS");
        for (int i = 0; i < claims.size(); i++) {
          claimList.add(claims.get(i));
        }
      }

      @Override
      public void onFailure(Call<List<Claim>> call, Throwable t) {
        Log.e("LOADING DATA FAILURE", "LOADING CLAIMS UNSUCCESSFUL");
      }
    });

  }

  private void setFirstFragment() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.recyclerView_claim_list);

    if (fragment == null) {
      fragment = new ClaimListFragment();
    }

    fm.beginTransaction()
        .add(R.id.fragment_container, fragment, "LIST_CLAIM")
        .addToBackStack("CLAIM_LIST_FRAGMENT")
        .commit();
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
      case R.id.nav_my_appartments:
        fragment = MyApptTabFragment.newInstance();
        TAG = "TAB_FRAGMENT";
        break;
      case R.id.nav_claims:

        fragment = ClaimListFragment.newInstance();
        TAG = "CLAIM_LIST_FRAGMENT";
        break;
      case R.id.nav_profile:
        fragment = Profile.newInstance();
        TAG = "PROFILE";
        break;
      case R.id.nav_settings:
        fragment = Settings.newInstance();
        TAG = "SETTINGS";
        break;
    }

    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction()
        .replace(R.id.fragment_container, fragment, TAG)
        .addToBackStack(TAG)
        .commit();


    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }


  @Override
  public void onClaimSelected() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag("CLAIM_DETAIL");
    if (fragment == null) {
      fragment = ClaimDetailFragment.newInstance();
    }
    isDrawerEnable(false);
    fm.beginTransaction()
        .replace(R.id.fragment_container, fragment, "CLAIM_DETAIL")
        .addToBackStack("CLAIM_DETAIL")
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

  @Override
  public void onApartmentSelected() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag("APARTMENT_DETAIL");
    if (fragment == null) {
      fragment = MyApptDetailFragment.newInstance();
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


}
