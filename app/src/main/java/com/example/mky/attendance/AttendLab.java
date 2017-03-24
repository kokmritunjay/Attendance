package com.example.mky.attendance;

/**
 * Created by mky on 6/18/2016.
 */
import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class AttendLab {
    private ArrayList<Attend> mAttends;
//   private ArrayList<Date> pDates;
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";
   // private static final String pFILENAME="presentdates.json";
    private JSONSerializer mSerializer;
   // private DatesJSONSerializer pSerializer;


    private static AttendLab sAttendLab;
    private Context mAppContext;

    private AttendLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new JSONSerializer(mAppContext, FILENAME);
     //   pSerializer= new DatesJSONSerializer(mAppContext,pFILENAME);

        try {
            mAttends = mSerializer.loadCrimes();
           // pDates=pSerializer.loadDates();
        } catch (Exception e) {
            mAttends = new ArrayList<Attend>();
            Log.e(TAG, "Error loading crimes: ", e);
        }

    }


    public static AttendLab get(Context c) {
        if (sAttendLab == null) {
            sAttendLab = new AttendLab(c.getApplicationContext());
        }
        return sAttendLab;
    }

    public Attend getAttend(UUID id) {
        for (Attend c : mAttends) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addAttend(Attend c) {
        mAttends.add(c);
    }

    public void deleteAttend(Attend c) {
        mAttends.remove(c);
        saveCrimes();
    }

    public boolean saveCrimes() {
        try {
            mSerializer.saveCrimes(mAttends);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {saveCrimes();
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
    }

        public ArrayList<Attend> getAttends () {
            return mAttends;
        }

    }


