package com.example.mky.attendance;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

public class AttendActivity extends AppCompatActivity {
    public static final String TAG = "com.example.mky.";
    public static final String EXTRA_CRIME_ID = "CRIME_ID";
    // public UUID uuid;
    Attend kAttend;
    public Date tempDate;
    public String kTitle;
    protected ArrayList<String> presentDate;
    protected ArrayList<String> absentDate;
    protected ArrayList<String> cancelDate;
    protected int it;
    public CaldroidFragment caldroidFragment = new CaldroidFragment();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_fragment);
        Bundle appleData = getIntent().getExtras();
        final UUID crimeId = (UUID) appleData.getSerializable(EXTRA_CRIME_ID);
        kAttend = AttendLab.get(getApplicationContext()).getAttend(crimeId);
        kTitle = new String(kAttend.getTitle());
        //final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        presentDate = new ArrayList<String>(kAttend.getPDate());
        absentDate = new ArrayList<String>(kAttend.getADate());
        cancelDate = new ArrayList<String>(kAttend.getCDate());
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);


        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        // Fragment abcd= manager.findFragmentById(R.id.calender1);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calender1, caldroidFragment);

        t.commit();
        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(java.util.Date date, View view) {
                showDialog(date);
                //SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd", Locale.getDefault());
               // ColorDrawable blue = new ColorDrawable(Color.BLUE);
                //  registerForContextMenu(view);
                //  openContextMenu(view);
                //caldroidFragment.setBackgroundDrawableForDate(blue, date);

                //Toast.makeText(getApplicationContext(), kAttend.getTitle() + df.format(date),
                //       Toast.LENGTH_SHORT).show();
                //caldroidFragment.refreshView();
                //kAttend.addPDate(date.toString());


            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;


                ColorDrawable blue = new ColorDrawable(Color.BLUE);
                ColorDrawable red = new ColorDrawable(Color.RED);
                ColorDrawable green = new ColorDrawable(Color.GREEN);
                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                for (int i = 0; i < absentDate.size(); i++) {

                    try {
                        Date date = (Date) formatter.parse(absentDate.get(i));
                        caldroidFragment.setBackgroundDrawableForDate(red, date);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < presentDate.size(); j++) {

                    try {
                        Date date = (Date) formatter.parse(presentDate.get(j));
                        caldroidFragment.setBackgroundDrawableForDate(green, date);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                for (int k = 0; k < cancelDate.size(); k++) {
                    try {
                        Date date = (Date) formatter.parse(cancelDate.get(k));
                        caldroidFragment.setBackgroundDrawableForDate(blue, date);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


              /*  for (Date date :presentDate){
                    ColorDrawable blue = new ColorDrawable(Color.BLUE);
                    caldroidFragment.setBackgroundDrawableForDate(blue, date);
                }
               */

                //Toast.makeText(getApplicationContext(), text,
                //       Toast.LENGTH_SHORT).show();
     /*          presentDate = new ArrayList<Date>(kAttend.getpDate());
                for (int i = 0; i < presentDate.size(); i++) {
                    Toast.makeText(getApplicationContext(), presentDate.get(i).toString(),
                            Toast.LENGTH_SHORT).show();
                }
             /*       stringArrayList = new ArrayList<>();
                SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd", Locale.getDefault());

                for (int i = 0; i < presentDate.size(); i++) {

                    for (int j = 1; j < 31; j++) {
                        startDate = new Date();
                        try {
                            startDate = df.parse(presentDate.get(i));
                            if((String.valueOf(year)+":"+String.valueOf(month)+":"+String.valueOf(j))==presentDate.get(i)){
                                ColorDrawable blue = new ColorDrawable(Color.BLUE);
                                Toast.makeText(getApplicationContext(), "done",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }
                caldroidFragment.refreshView();
                */
            }

            @Override
            public void onLongClickDate(java.util.Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click ",
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCaldroidViewCreated() {
                Toast.makeText(getApplicationContext(),
                        "Caldroid view is created",
                        Toast.LENGTH_LONG).show();

            }

        };
        caldroidFragment.setCaldroidListener(listener);

        if (fragment == null) {
            fragment = new AttendFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)

                    .commit();


        }

    }

    @Override
    public void onPause() {
        super.onPause();
        AttendLab.get(getApplicationContext()).saveCrimes();
    }
public void showDialog(final Date date){
    final ColorDrawable blue = new ColorDrawable(Color.BLUE);
    final ColorDrawable red = new ColorDrawable(Color.RED);
    final ColorDrawable green = new ColorDrawable(Color.GREEN);
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    final String []colors={"Present","Absent","Cancelled"};
    dialogBuilder.setTitle("choose a color");
    dialogBuilder.setIcon(R.drawable.ic_menu_share);

    dialogBuilder.setSingleChoiceItems(colors, -1, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==0){

              /*  caldroidFragment.setBackgroundDrawableForDate(green, date);
                caldroidFragment.refreshView();
                kAttend.addPDate(date.toString());
                */
               it=0;

            }
             if(which==1){
                     it=1;
            }
             if(which==2){
                 it=2;
                  }

        }
    });
    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });
    dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(it==0){
                if(absentDate.contains(date.toString())){
                    kAttend.delADate(date.toString());
                    caldroidFragment.setBackgroundDrawableForDate(green, date);
                    caldroidFragment.refreshView();
                    kAttend.addPDate(date.toString());

                }
                 else if(cancelDate.contains(date.toString())){
                    kAttend.delCDate(date.toString());
                    caldroidFragment.setBackgroundDrawableForDate(green, date);
                    caldroidFragment.refreshView();
                    kAttend.addPDate(date.toString());

                }
                else if (presentDate.contains(date.toString())){
                    caldroidFragment.setBackgroundDrawableForDate(green, date);
                    caldroidFragment.refreshView();
                }
                else{
                    caldroidFragment.setBackgroundDrawableForDate(green, date);
                    caldroidFragment.refreshView();
                    kAttend.addPDate(date.toString());
                }

            }
           else if(it==1){
                if(presentDate.contains(date.toString())){
                    kAttend.delPDate(date.toString());
                    caldroidFragment.setBackgroundDrawableForDate(red, date);
                    kAttend.addADate(date.toString());
                    caldroidFragment.refreshView();
                }
               else if(cancelDate.contains(date.toString())){
                    kAttend.delCDate(date.toString());
                    caldroidFragment.setBackgroundDrawableForDate(red, date);
                    kAttend.addADate(date.toString());
                    caldroidFragment.refreshView();

                }
                else if(absentDate.contains(date.toString())){
                    caldroidFragment.setBackgroundDrawableForDate(red, date);
                    caldroidFragment.refreshView();

                }
                else{
                    caldroidFragment.setBackgroundDrawableForDate(red, date);
                    kAttend.addADate(date.toString());
                    caldroidFragment.refreshView();

                }
            }
         else if (it==2){
                if(presentDate.contains(date.toString())){

                    kAttend.delPDate(date.toString());
                    caldroidFragment.setBackgroundDrawableForDate(blue, date);
                    caldroidFragment.refreshView();
                    kAttend.addCDate(date.toString());
                }
               else if(absentDate.contains(date.toString())){
                    kAttend.delADate(date.toString());
                    caldroidFragment.setBackgroundDrawableForDate(blue, date);
                    caldroidFragment.refreshView();
                    kAttend.addCDate(date.toString());

                }
               else if (cancelDate.contains(date.toString())){
                    caldroidFragment.setBackgroundDrawableForDate(blue, date);
                    caldroidFragment.refreshView();
                }
                else{
                    caldroidFragment.setBackgroundDrawableForDate(blue, date);
                    caldroidFragment.refreshView();
                    kAttend.addCDate(date.toString());
                }

            }
            else{}
            it=4;
        }
    });

    AlertDialog alertDialog= dialogBuilder.create();
    alertDialog.show();

}
/*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.attend_options,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.present_choice:
                SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd", Locale.getDefault());
                ColorDrawable green = new ColorDrawable(Color.GREEN);
                caldroidFragment.setBackgroundDrawableForDate(green, tempDate);
                caldroidFragment.refreshView();
                kAttend.addPDate(tempDate.toString());
                item.setChecked(true);
                return true;
            case R.id.absent_choice:
                // SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd", Locale.getDefault());
                ColorDrawable red = new ColorDrawable(Color.RED);
                caldroidFragment.setBackgroundDrawableForDate(red, tempDate);
                caldroidFragment.refreshView();
                kAttend.addPDate(tempDate.toString());
                item.setChecked(true);
                return true;
            case R.id.cancel_choice:
                //SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd", Locale.getDefault());
                ColorDrawable blue = new ColorDrawable(Color.BLUE);
                caldroidFragment.setBackgroundDrawableForDate(blue, tempDate);
                caldroidFragment.refreshView();
                kAttend.addPDate(tempDate.toString());
                item.setChecked(true);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
