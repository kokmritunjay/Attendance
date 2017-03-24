package com.example.mky.attendance;

/**
 * Created by mky on 7/2/2016.
 */


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Date;
import java.util.UUID;


public class TimeTableAdaptor extends Fragment {
    public static final String TAG = "com.example.mky.";
    public static final String EXTRA_CRIME_ID = "criminalintent.CRIME_ID1";


    private static final String DIALOG_TIME = "time";
    private static final int REQUEST_TIME = 1;
    int b1=0;
    int b2=0;
    int b3=0;
    int b4=0;
    int b5=0;
    int b6=0;
    int b7=0;

    Attend mCrime;
    //EditText mTitleField;
    Button mTimeButton1;
    Button mTimeButton2;
    Button mTimeButton3;
    Button mTimeButton4;
    Button mTimeButton5;
    Button mTimeButton6;
    Button mTimeButton7;


  //  CheckBox mSolvedCheckBox;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID crimeId = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mCrime = AttendLab.get(getActivity()).getAttend(crimeId);
    }

 /*   public void updateDate() {
        //mDateButton.setText(mCrime.getDate().toString());
    }
*/
 public void updateDate() {

     mTimeButton1.setText(mCrime.getmMonday().toString().substring(11,16));
     mTimeButton2.setText(mCrime.getmTuesday().toString().substring(11,16));
     mTimeButton3.setText(mCrime.getmWednesday().toString().substring(11,16));
     mTimeButton4.setText(mCrime.getmThrusday().toString().substring(11,16));
     mTimeButton5.setText(mCrime.getmFriday().toString().substring(11,16));
     mTimeButton6.setText(mCrime.getmSaturday().toString().substring(11,16));
     mTimeButton7.setText(mCrime.getmSunday().toString().substring(11,16));

 }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.time_table, parent, false);


    /*    mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

       */
        mTimeButton1 = (Button) v.findViewById(R.id.button);
        mTimeButton2 = (Button) v.findViewById(R.id.button2);
        mTimeButton3 = (Button) v.findViewById(R.id.button3);
        mTimeButton4 = (Button) v.findViewById(R.id.button4);
        mTimeButton5 = (Button) v.findViewById(R.id.button5);
        mTimeButton6 = (Button) v.findViewById(R.id.button6);
        mTimeButton7 = (Button) v.findViewById(R.id.button7);
        updateDate();

        mTimeButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmMonday());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
                b1=1; b2=0; b3=0; b4=0; b5=0; b6=0; b7=0;

            }
        });
        mTimeButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmTuesday());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
                b1=0; b2=1; b3=0; b4=0; b5=0; b6=0; b7=0;
            }
        });
        mTimeButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmWednesday());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
                b1=0; b2=0; b3=1; b4=0; b5=0; b6=0; b7=0;
            }
        });
        mTimeButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmThrusday());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
                b1=0; b2=0; b3=0; b4=1; b5=0; b6=0; b7=0;
            }
        });
        mTimeButton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmFriday());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
                b1=1; b2=0; b3=0; b4=0; b5=1; b6=0; b7=0;
            }
        });
        mTimeButton6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmSaturday());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
                b1=1; b2=0; b3=0; b4=0; b5=0; b6=1; b7=0;
            }
        });
        mTimeButton7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmSunday());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
                b1=1; b2=0; b3=0; b4=0; b5=0; b6=0; b7=1;
            }
        });

       /* mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });


*/

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_TIME) {
            Date date = (Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            if(b1==1)
            mCrime.setmMonday(date);
            if(b2==1)
                mCrime.setmTuesday(date);
            if(b3==1)
                mCrime.setmWednesday(date);
            if(b4==1)
                mCrime.setmThrusday(date);
            if(b5==1)
                mCrime.setmFriday(date);
            if(b6==1)
                mCrime.setmSaturday(date);
            if(b7==1)
                mCrime.setmSunday(date);
            updateDate();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        AttendLab.get(getActivity()).saveCrimes();
    }

}














































































/*
 class TimeTableAdaptor extends ArrayAdapter<String> {
     private Button mTimeButton;
     private static final int REQUEST_TIME = 1;
    public TimeTableAdaptor(Context context, String [] foods) {
        super(context, R.layout.time_table,foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater buckysInflator = LayoutInflater.from(getContext());
        View customView = buckysInflator.inflate(R.layout.time_table,parent,false);
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("C",color1);

        ImageView image = (ImageView) customView.findViewById(R.id.imageButton);
        mTimeButton = (Button)customView.findViewById(R.id.crime_time);
        mTimeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(TimeTableAdaptor.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
            }
        });
       /* final EditText editText=(EditText) customView.findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TimeTableAdaptor.super.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute,false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });
        //
         image.setImageDrawable(drawable);
        String singleFoodItem = getItem(position);
        TextView buckysText = (TextView)customView.findViewById(R.id.buckysText);
        //ImageView buckysImage = (ImageView) customView.findViewById(R.id.imageButton);
        buckysText.setText(singleFoodItem);
        image.setImageDrawable(drawable);

        return customView;
    }

                }


*/
