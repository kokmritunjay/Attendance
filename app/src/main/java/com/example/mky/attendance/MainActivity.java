package com.example.mky.attendance;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int menuItem;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(menuItem==0)
        this.recreate();

   if(menuItem==R.id.nav_gallery){
         getSupportActionBar().setTitle("Time Table");
        }
        else{
       getSupportActionBar().setTitle("Attendance");
       this.recreate();
       menuItem=0;

   }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<Attend> mCrimes=new ArrayList<>(AttendLab.get(getApplicationContext()).getAttends());
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        AlarmManager alarmManagerm = (AlarmManager) getSystemService(ALARM_SERVICE);
        for(int i=0;i<mCrimes.size();i++){
            Date date = mCrimes.get(i).getmMonday();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
            calendar.set(Calendar.MINUTE, date.getMinutes());
            calendar.set(Calendar.SECOND, date.getSeconds());
            if(System.currentTimeMillis()<=calendar.getTimeInMillis()){
            Intent intent = new Intent(MainActivity.this, Notification_reciever.class);
            //int numb=(int)(Math.random()*1000000);
              int numb= (int)calendar.getTimeInMillis();
            intent.putExtra(Notification_reciever.EXTRA_CRIME_ID,numb);
                intent.putExtra(Notification_reciever.EXTRA_CRIME_ID1,mCrimes.get(i).getTitle());
            // int numb=(int)(Math.random()*1000000);
              //  intentArray.add(PendingIntent.getBroadcast(MainActivity.this,numb,intent,PendingIntent.FLAG_UPDATE_CURRENT));
             PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,numb,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 120000, pendingIntent);}
        }


       // setTitle("Attendance");

       /* FragmentManager manager = getSupportFragmentManager();
       Fragment fragment = manager.findFragmentById(R.id.fragmentContainer1);

        if (fragment == null) {
            fragment = new AttendListFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer1, fragment)
                    .commit();

        }
*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attend crime = new Attend ();
                menuItem=0;
                AttendLab.get(getApplicationContext()).addAttend(crime);
                Intent i = new Intent(getBaseContext(), AddAttendActivity.class);
                i.putExtra(AddAttendFragment.EXTRA_CRIME_ID, crime.getId());
                i.putExtra(AddAttendActivity.EXTRA_CRIME_ID,crime.getId());

                startActivityForResult(i,0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            navigationView.getMenu().performIdentifierAction(R.id.nav_camera, 0);
        }
        navigationView.getMenu().performIdentifierAction(R.id.nav_camera, 0);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
           if (id == R.id.action_settings) {
                Intent in = new Intent(this,SettingsActivity.class);
                startActivityForResult(in ,0);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getSupportFragmentManager();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            getSupportActionBar().setTitle("Attendance");
           Fragment fragment1 = new AttendListFragment();
            menuItem =item.getItemId();
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer1, fragment1)
                    .commit();
           // setTitle("Attendance");

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            menuItem=item.getItemId();
            getSupportActionBar().setTitle("Time Table");
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer1, new TimeTableListFragment())
                    .commit();




        } else if (id == R.id.nav_slideshow) {
            Intent inten = new Intent(this,SettingsActivity.class);
            startActivityForResult(inten,0);

        } else if (id == R.id.nav_manage) {
            menuItem=item.getItemId();
            getSupportActionBar().setTitle("Time Table");
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer1, new WeekDaysFragment())
                    .commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
