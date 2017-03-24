package com.example.mky.attendance;

import java.text.DecimalFormat;
import java.util.*;
import java.lang.String;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
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
import android.widget.ListView;
import android.widget.TextView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class AttendListFragment extends ListFragment {
    public static final String TAG="com.example.mky";

    private ArrayList<Attend> mCrimes;
    private boolean mSubtitleVisible;
    String s;
    String p;
    String a;
    String per;
    double it=0.00;
    String itn;
    protected ArrayList<String> presentDate;
    protected ArrayList<String> absentDate;
    protected ArrayList<String> cancelDate;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (mSubtitleVisible) {
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }
        ListView listView = (ListView) v.findViewById(android.R.id.list);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            registerForContextMenu(listView);
        } else {
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {

                }

                @Override
                public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.attend_list_item_context, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_delete_crime:
                            CrimeAdapter adapter = (CrimeAdapter) getListAdapter();
                            AttendLab crimeLab = AttendLab.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    crimeLab.deleteAttend(adapter.getItem(i));
                                }
                            }
                            mode.finish();
                            adapter.notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }

                }

                @Override
                public void onDestroyActionMode(android.view.ActionMode mode) {

                }
            });
        }
        return v;
    }
                /*

                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.attend_list_item_context, menu);
                    return true;
                }

                public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                      long id, boolean checked) {
                }

                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_delete_crime:
                            CrimeAdapter adapter = (CrimeAdapter)getListAdapter();
                            AttendLab crimeLab = AttendLab.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    crimeLab.deleteAttend(adapter.getItem(i));
                                }
                            }
                            mode.finish();
                            adapter.notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }
                }

                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                public void onDestroyActionMode(ActionMode mode) {

                }
            });

        }

        return v;
    }
*/
    public void onListItemClick(ListView l, View v, int position, long id) {
        // get the Crime from the adapter
        Attend c = ((CrimeAdapter)getListAdapter()).getItem(position);
        // start an instance of CrimePagerActivity
        Intent i = new Intent(getActivity(), AttendActivity.class);
        i.putExtra(AttendFragment.EXTRA_CRIME_ID, c.getId());
        i.putExtra(AttendActivity.EXTRA_CRIME_ID,c.getId());
        startActivityForResult(i,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
       /* MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible && showSubtitle != null) {
            showSubtitle.setTitle(R.string.hide_subtitle);
        }
        */

    }

/*
    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Attend crime = new Attend ();
                AttendLab.get(getActivity()).addAttend(crime);
                Intent i = new Intent(getActivity(), AttendActivity.class);
                i.putExtra(AttendFragment.EXTRA_CRIME_ID, crime.getId());


                startActivityForResult(i,0);

                return true;
            case R.id.menu_item_show_subtitle:
                if (getActivity().getActionBar().getSubtitle() == null) {
                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
                    mSubtitleVisible = true;
                    item.setTitle(R.string.hide_subtitle);
                }  else {
                    getActivity().getActionBar().setSubtitle(null);
                    mSubtitleVisible = false;
                    item.setTitle(R.string.show_subtitle);
                }
                return true;

           case R.id.action_settings:
                Intent in = new Intent(getActivity(),SettingsActivity.class);
                startActivityForResult(in,0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.attend_list_item_context, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        CrimeAdapter adapter = (CrimeAdapter)getListAdapter();
        Attend crime = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_crime:
                AttendLab.get(getActivity()).deleteAttend(crime);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
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

            p="0";

            Attend c = getItem(position);
            presentDate = new ArrayList<String>(c.getPDate());
            absentDate = new ArrayList<String>(c.getADate());
            cancelDate = new ArrayList<String>(c.getCDate());
            Log.i(TAG,"absent date= "+absentDate.toString());
            Log.i(TAG,"present date= "+presentDate.toString());
            if(absentDate.isEmpty()){
                a = "0";
               // Log.i(TAG,"absentDate.isEmpty() is executed");
            }
            else if (absentDate.get(0).length()<=3){
                a = String.valueOf(absentDate.size()-1) ;
              //  Log.i(TAG,"absentDate.get(0).length()<=3 is executed");
            }
            else{
                a=String.valueOf(absentDate.size());
              //  Log.i(TAG,"String.valueOf(absentDate.size()) is executed");
            }
            if(presentDate.isEmpty()){
                p = "0";
             }
            else if(presentDate.get(0).length()<=3){
                p = String.valueOf(presentDate.size()-1) ;
            }
            else{
                p=String.valueOf(presentDate.size());
            }

               /*     it = (absentDate.size() + presentDate.size());
                    it = (double) (presentDate.size()) / it;
                    it = it * 100;
*/

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView presentTextView =(TextView)convertView.findViewById(R.id.present_text);
            presentTextView.setText("Present: "+p);
            TextView absentTextView =(TextView)convertView.findViewById(R.id.absent_text);
            absentTextView.setText("Absent: "+a);
            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
            itn= preferences.getString("example_text","0");
            TextView sugsstTextView = (TextView) convertView.findViewById(R.id.suggestion_text);
            if(Integer.parseInt(p)+Integer.parseInt(a)!=0) {
                it = Integer.parseInt(a) + Integer.parseInt(p);
                // Log.i(TAG, "val of it after a+p" + String.valueOf(it));
                it = (double) (Integer.parseInt(p)) / it;
               // Log.i(TAG, "value after div" + String.valueOf(it));
                it = it * 100;
                //Log.i(TAG, String.valueOf(it));
            }
            DecimalFormat df = new DecimalFormat("#.##");
            if((presentDate.size())!=0){
                //  per = Integer.toString((c.getPDate().size()-1/(c.getADate().size()+c.getPDate().size()))*100) ;
                per=df.format(it);
                //  it=0.00;

                if(Integer.parseInt(itn)>it)
                    sugsstTextView.setText("Attend next classes");
                else{
                    sugsstTextView.setText("You are on the track!");
                }
                it=0;
            }
            else{
                per="0.0";
                sugsstTextView.setText("You are on the track!");
            }
            TextView percentageTextView =(TextView)convertView.findViewById(R.id.percentage_text);
            percentageTextView.setText("%: "+per);




            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color1 = generator.getRandomColor();
            if (c.getTitle()==null || c.getTitle()=="")
                s="";
            else{
                try{
            s=new String((c.getTitle()).substring(0,1));}
            catch (StringIndexOutOfBoundsException e){
                e.printStackTrace();
            }
            }
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(s,color1);
            s="";
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

    @Override
    public void onPause() {
        super.onPause();
        AttendLab.get(getActivity()).saveCrimes();
    }
}

