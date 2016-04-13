package com.example.michal.navigationdrawer.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michal.navigationdrawer.R;
import com.example.michal.navigationdrawer.ui.claims.detail.CalimDetailFragment;
import com.example.michal.navigationdrawer.ui.claims.list.ClaimListFragment;
import com.example.michal.navigationdrawer.ui.apartment.detail.MyApptDetailFragment;
import com.example.michal.navigationdrawer.ui.apartment.list.MyApptFragment;

public class RentMateActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ClaimListFragment.Callbacks, MyApptFragment.Callbacks {

    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.recyclerView_claim_list);

        if (fragment == null) {
            fragment = new ClaimListFragment();
        }

        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        switch (id) {
            case R.id.nav_my_appartments:
                fragment = MyApptFragment.newInstance();
                break;
            case R.id.nav_claims:
                fragment = ClaimListFragment.newInstance();
                break;
            default:
                fragment = MyApptFragment.newInstance();
        }
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClaimSelected() {

        Fragment fragment = fm.findFragmentById(R.id.fragment_claim_detail);
        if (fragment == null) {
            fragment = new CalimDetailFragment();
        }

        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onAppartmentSelected() {

        Fragment fragment = fm.findFragmentById(R.id.fragment_claim_detail);
        if (fragment == null) {
            fragment = new MyApptDetailFragment();
        }

        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


}
