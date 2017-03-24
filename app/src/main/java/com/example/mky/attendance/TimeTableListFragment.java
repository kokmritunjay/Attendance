package com.example.mky.attendance;

import java.util.*;
import java.lang.String;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.AbsListView;
import android.widget.TextView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class TimeTableListFragment extends ListFragment {

    private ArrayList<Attend> mCrimes;
    private boolean mSubtitleVisible;
    String s;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mCrimes = AttendLab.get(getActivity()).getAttends();
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
        setRetainInstance(true);
        mSubtitleVisible = false;
        adapter.notifyDataSetChanged();
    }



    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }




    public void onListItemClick(ListView l, View v, int position, long id) {
        // get the Crime from the adapter
        Attend c = ((CrimeAdapter)getListAdapter()).getItem(position);
        // start an instance of CrimePagerActivity
        Intent i = new Intent(getActivity(), SetTimeTableActivity.class);
       i.putExtra(TimeTableAdaptor.EXTRA_CRIME_ID, c.getId());
        //i.putExtra(AttendActivity.EXTRA_CRIME_ID,c.getId());

        startActivityForResult(i,0);
    }
    private class CrimeAdapter extends ArrayAdapter<Attend> {
        public CrimeAdapter(ArrayList<Attend> crimes) {
            super(getActivity(), android.R.layout.simple_list_item_1, crimes);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_attend, null);
            }

            // configure the view for this Crime
            Attend c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color1 = generator.getRandomColor();
            if (c.getTitle()==null)
                s="m";
            else
                s=new String((c.getTitle()).substring(0,1));
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(s,color1);
            ImageView image = (ImageView) convertView.findViewById(R.id.imageButton);
            image.setImageDrawable(drawable);
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }

}

