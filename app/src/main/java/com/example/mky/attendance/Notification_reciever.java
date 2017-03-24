package com.example.mky.attendance;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mky on 7/5/2016.
 */
public class Notification_reciever extends BroadcastReceiver {
    public static final String EXTRA_CRIME_ID="CRIME_ID";
    public static final String EXTRA_CRIME_ID1="CRIME_ID";
    public static final String TAG = "om.example.mky.services";
    //Log.i(TAG,"onStartCommand method called");
    @Override
    public void onReceive(Context context, Intent intent) {

              // int st = Integer.parseInt(intent.getStringExtra(EXTRA_CRIME_ID));
              // Intent background= new Intent(context,MyService.class);
             // background.putExtra(MyService.EXTRA_CRIME_ID,st);
              // context.startService(background);


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            Intent repeating_intent = new Intent(context, Repeating_Activity.class);
            repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.i(TAG,"onStartCommand method called");
        int st = intent.getIntExtra(EXTRA_CRIME_ID,0);
        String str=intent.getStringExtra(EXTRA_CRIME_ID1);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,st, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                    setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.noti)
                    .setContentTitle("Attendance")
                    .setContentText(str)
                    .setAutoCancel(true)
                    .setSound(alarmSound);

            notificationManager.notify(st, builder.build());

        }
    }

























