package com.example.michal.navigationdrawer.ui.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Michal on 10/04/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TenantLogInFragment();
                break;
            case 1:
                fragment = new TenantLandlordFragment();
                break;
            case 2:
                fragment = new LandlordLogInFragment();
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 3;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title = "";
//        switch (position) {
//            case 0:
//                title = "Tenant&Landlord";
//                break;
//            case 1:
//                title = "TenantLogIn";
//                break;
//            case 2:
//                title = "LandlordLogIn";
//                break;
//        }
//        return title;
//    }
}
