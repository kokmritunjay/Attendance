package com.example.mky.attendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mky on 7/9/2016.
 */
public class PasswordActivity extends AppCompatActivity {
    boolean auth;
    EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(null);
        setupActionBar();
        setContentView(R.layout.password_activity);
        editText = (EditText) findViewById(R.id.password);
       SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
         boolean auth = preferences.getBoolean("authentication_key",false);
        if (auth==false){
           changeActivity();
        }

    }
    public void Go(View view){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String str = preferences.getString("password_key","");

        if(str.equals(editText.getText().toString())) {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if(str==""){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    public void changeActivity(){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);


    }
}
