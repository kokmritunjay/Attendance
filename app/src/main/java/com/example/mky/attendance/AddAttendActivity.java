package com.example.mky.attendance;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.*;


public class AddAttendActivity extends FragmentActivity {
    Attend kAttend;
    public static final String TAG = "com.example.mky.";
    public static final String EXTRA_CRIME_ID="CRIME_ID";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_fragment);
        Bundle appleData = getIntent().getExtras();
        final UUID crimeId = (UUID) appleData.getSerializable(EXTRA_CRIME_ID);
        kAttend = AttendLab.get(getApplicationContext()).getAttend(crimeId);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.buckysview);
        // Fragment abcd= manager.findFragmentById(R.id.calender1);


        if (fragment == null) {
            fragment = new AddAttendFragment();
            manager.beginTransaction()
                    .add(R.id.buckysview, fragment)

                    .commit();


        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if(kAttend.getTitle()!=null) {
            /*
            Toast.makeText(getApplicationContext(), kAttend.getmMonday().toString(), Toast.LENGTH_LONG).show();
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
           Date date = kAttend.getmMonday();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
            calendar.set(Calendar.MINUTE, date.getMinutes());
            calendar.set(Calendar.SECOND, date.getSeconds());
            Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent)
            ;
            */
        }
        if(kAttend.getTitle()==null || kAttend.getTitle()==""){
            AttendLab crimeLab = AttendLab.get(getApplicationContext());
            crimeLab.deleteAttend(kAttend);
        }

    }

}


    /*@Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(AttendFragment.EXTRA_CRIME_ID);
        return AttendFragment.newInstance(crimeId);
    }
}

*/
/*   public class CrimeActivity extends FragmentActivity {
        // Called when the activity is first created.
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fragment);
            CaldroidFragment caldroidFragment = new CaldroidFragment();
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            caldroidFragment.setArguments(args);


            FragmentManager manager = getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
           // Fragment abcd= manager.findFragmentById(R.id.calender1);
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.calender1, caldroidFragment);
            t.commit();

            if (fragment == null) {
                fragment = new AttendFragment();
                manager.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
        }
    }
*/

  /*  @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(AttendFragment.EXTRA_CRIME_ID);
        return AttendFragment.newInstance(crimeId);
    }
*/


/*
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new AttendFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

*/
