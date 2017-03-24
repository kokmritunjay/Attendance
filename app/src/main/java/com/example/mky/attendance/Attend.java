package com.example.mky.attendance;

/**
 * Created by mky on 6/18/2016.
 */

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

public class Attend {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private ArrayList<String> pDate;
    private ArrayList<String> aDate;
    private ArrayList<String> cDate;
    private boolean mSolved;
    private Date mTuesday;
    private Date mWednesday;
    private Date mThursday;
    private Date mFriday;
    private Date mSaturday;
    private Date mSunday;
    private Date mMonday;
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";
    private static final String JSON_MONDAY="monday";
    private static final String JSON_TUESDAY="tuesday";
    private static final String JSON_WEDNESDAY="wednesday";
    private static final String JSON_THURSDAY="thrusday";
    private static final String JSON_FRIDAY="friday";
    private static final String JSON_SATURDAY="saturday";
    private static final String JSON_SUNDAY="sunady";


    private static final String JSON_PARRAY = "json_parray";
    private static final String JSON_AARRAY = "json_aarray";
    private static final String JSON_CARRAY = "json_carray";
    private static final String TAG = "example.mky.attendance";
    // private static final Integer JSON_ARRAY = 1;
    public Attend() {
        mId = UUID.randomUUID();
        mDate = new Date();
        pDate = new ArrayList<>();
        aDate = new ArrayList<>();
        cDate = new ArrayList<>();
        mMonday=new Date();
        mTuesday=new Date();
        mWednesday=new Date();
        mThursday=new Date();
        mFriday=new Date();
        mSaturday=new Date();
        mSunday =new Date();

    }


    public Attend(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
        mMonday=new Date (json.getLong(JSON_MONDAY));
        mTuesday=new Date (json.getLong(JSON_TUESDAY));
        mWednesday=new Date (json.getLong(JSON_WEDNESDAY));
        mThursday=new Date (json.getLong(JSON_THURSDAY));
        mFriday=new Date (json.getLong(JSON_FRIDAY));
        mSaturday=new Date (json.getLong(JSON_SATURDAY));
        mSunday=new Date (json.getLong(JSON_SUNDAY));

        String string =json.getString(JSON_PARRAY);

        string=string.substring(1,string.length()-1);
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(string.split(", ")));
       // StringTokenizer parser = new StringTokenizer(string);
        Log.i(TAG,string);
        pDate= new ArrayList<String>();
        for (int i = 0; i < myList.size(); i++)
            pDate.add(myList.get(i));

        String string1 = json.getString(JSON_AARRAY);
        string1=string1.substring(1,string1.length()-1);
        ArrayList<String>myList1= new ArrayList<>(Arrays.asList(string1.split(", ")));
        aDate = new ArrayList<String>();
        for (int j=0; j<myList1.size();j++)
            aDate.add(myList1.get(j));

        String string2 = json.getString(JSON_CARRAY);
        string2=string2.substring(1,string2.length()-1);
        ArrayList<String>myList2= new ArrayList<>(Arrays.asList(string2.split(", ")));
        cDate = new ArrayList<String>();
        for (int k=0; k<myList2.size();k++)
            cDate.add(myList2.get(k));

        //Type collectionType2 = new TypeToken<ArrayList<Date>>() {}.getType();
        //ArrayList<Date> listObj = gson.fromJson(string, collectionType2);
       // for (int i = 0; i < listObj.size(); i++) {
        //    pDate.add(listObj.get(i));
       // }

   //     JSONArray jArray= json.getJSONArray(JSON_ARRAY);
       //  ArrayList<Date> jpDate= new ArrayList<Date>();
       /* Type collectionType2 = new TypeToken<ArrayList<Date>>() {}.getType();
        ArrayList<Date> listObj = gson.fromJson(JSON_ARRAY, collectionType2);
        for (int i = 0; i < listObj.size(); i++) {
            pDate.add(listObj.get(i));
        }
        */
        /* JSONArray jArray = json.getJSONArray(JSON_ARRAY);
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    pDate.add(((Date) jArray.get(i)));
                }
            }
            */

    }


 /*   public Attend (JSONArray jsonArray) throws JSONException{
        pDate=new ArrayList<Date>();
        pDate.add(new Date(jsonArray.getLong(JSON_ARRAY)));
    }
*/
    public JSONObject toJSON() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime());
        json.put(JSON_MONDAY,mMonday.getTime());
        json.put(JSON_TUESDAY,mTuesday.getTime());
        json.put(JSON_WEDNESDAY,mWednesday.getTime());
        json.put(JSON_THURSDAY,mThursday.getTime());
        json.put(JSON_FRIDAY,mFriday.getTime());
        json.put(JSON_SATURDAY,mSaturday.getTime());
        json.put(JSON_SUNDAY,mSunday.getTime());
        json.put(JSON_PARRAY,pDate);
        json.put(JSON_AARRAY,aDate);
        json.put(JSON_CARRAY,cDate);
        return json;
    }

public ArrayList<String> getPDate(){
    return pDate;
}
    public void addPDate(String date){
        pDate.add(date);
    }
    public void delPDate(String date){
        pDate.remove(date);
    }

    public ArrayList<String> getADate(){
        return aDate;
    }
    public void addADate(String date){
        aDate.add(date);
    }
    public void delADate(String date){
        aDate.remove(date);
    }


    public ArrayList<String> getCDate(){
        return cDate;
    }
    public void addCDate(String date){
        cDate.add(date);
    }
    public void delCDate(String date){
        cDate.remove(date);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
    public Date getmMonday(){
        return mMonday;
    }
    public void setmMonday(Date date){
        mMonday=date;
    }

    public Date getmTuesday() {
        return mTuesday;
    }

    public void setmTuesday(Date mTuesday) {
        this.mTuesday = mTuesday;
    }

    public Date getmWednesday() {
        return mWednesday;
    }

    public void setmWednesday(Date mWednesday) {
        this.mWednesday = mWednesday;
    }

    public Date getmThrusday() {
        return mThursday;
    }

    public void setmThrusday(Date mThrusday) {
        this.mThursday = mThrusday;
    }

    public Date getmFriday() {
        return mFriday;
    }

    public void setmFriday(Date mFriday) {
        this.mFriday = mFriday;
    }

    public Date getmSaturday() {
        return mSaturday;
    }

    public void setmSaturday(Date mSaturday) {
        this.mSaturday = mSaturday;
    }

    public Date getmSunday() {
        return mSunday;
    }

    public void setmSunday(Date mSunday) {
        this.mSunday = mSunday;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
    @Override
    public String toString(){
        return mTitle;
    }


}
