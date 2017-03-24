package com.example.mky.attendance;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyService extends Service {

    public static final String TAG = "om.example.mky.services";
    public static final String EXTRA_CRIME_ID="CRIME_ID";
    public MyService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand method called");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                /*
              // code goes here
                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                AlarmManager alarmManagerm = (AlarmManager) getSystemService(ALARM_SERVICE);
                ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
                ArrayList<Attend> mCrimes=new ArrayList<>(AttendLab.get(getApplicationContext()).getAttends());

                for(int i=0;i<mCrimes.size();i++){
                    Date date = mCrimes.get(i).getmMonday();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
                    calendar.set(Calendar.MINUTE, date.getMinutes());
                    calendar.set(Calendar.SECOND, date.getSeconds());
                    Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
                    intent.putExtra(Notification_reciever.EXTRA_CRIME_ID,i);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),i,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManagerm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                }
*/
            }
        };
        Thread buckysThread = new Thread(r);
        buckysThread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy method called");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return null;
    }
}
