package com.example.mky.attendance;

/**
 * Created by mky on 6/18/2016.
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;

import com.roomorama.caldroid.CaldroidFragment;

public class AttendPagerActivity extends FragmentActivity {
    ViewPager mViewPager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        final ArrayList<Attend> crimes = AttendLab.get(this).getAttends();

        FragmentManager fm = getSupportFragmentManager();


        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public int getCount() {
                return crimes.size();
            }
            @Override
            public Fragment getItem(int pos) {
                UUID crimeId =  crimes.get(pos).getId();
                //return AttendFragment.newInstance(crimeId);
                return null;
            }
        });

        UUID crimeId = (UUID)getIntent().getSerializableExtra(AttendFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < crimes.size(); i++) {
            if (crimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
