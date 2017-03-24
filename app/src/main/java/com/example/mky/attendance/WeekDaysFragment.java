package com.example.mky.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mky on 1/3/2017.
 */
public class WeekDaysFragment extends ListFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ArrayList<String>foods= new ArrayList<String>();
       // String[] foods = {"Bacon", "ham", "tuna", "candy", "meatBall", "potato"};
        foods.add("eded32333");
        foods.add("edeeddd3");
        foods.add("edeadedd3");
        foods.add("esded3");
        foods.add("eded123");

        ListAdapter buckysAdapter = new CustomAdaptor(foods);
        setListAdapter(buckysAdapter);
        //ArrayAdapter<String> buckysAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1,foods);

    }

    private class CustomAdaptor extends ArrayAdapter<String> {
        public CustomAdaptor(ArrayList<String> foods) {
            super(getActivity(), android.R.layout.simple_list_item_1, foods);
        }
    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        // get the Crime from the adapter
        // start an instance of CrimePagerActivity
      /*  Toast.makeText(getContext(),"ddsass",Toast.LENGTH_LONG).show();
        TimeTableListFragment nextFrag= new TimeTableListFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer1, nextFrag)
                .addToBackStack(null)
                .commit();

                */

         Intent i = new Intent(getActivity(),TimeTableNewActivity.class );
        //i.putExtra(TimeTableAdaptor.EXTRA_CRIME_ID, c.getId());
        //i.putExtra(AttendActivity.EXTRA_CRIME_ID,c.getId());

       startActivityForResult(i,0);
    }
}