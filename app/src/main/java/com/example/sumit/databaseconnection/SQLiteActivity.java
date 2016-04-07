package com.example.sumit.databaseconnection;

/**
 * Created by Sumit on 3/2/2016.
 */

import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import java.util.Date;
import android.widget.EditText;

import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;


public class SQLiteActivity extends Activity {

    public int cntr=0;
    private SimpleDateFormat s=new SimpleDateFormat("MM/dd/yyyy-hh:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sqlite);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        cntr=sp.getInt("SQL_COUNTER", 0);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //create object of shared preference
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        cntr=sp.getInt("SQL_COUNTER", 0);
    }


    public void saveMessage(View view)
    {
        //create object of edittext
        EditText eText=(EditText)findViewById(R.id.msg);
        String msg=eText.getText().toString();
        DataController dc=new DataController(getBaseContext());
        dc.open();
        long rValue= dc.insert(msg);
        dc.close();
        if(rValue!=-1)
        {
            Context c = getApplicationContext();
            CharSequence text=getString(R.string.save_success_msg);
            int duration=Toast.LENGTH_LONG;
            Toast.makeText(c, text, duration).show();

            try
            {
                cntr+=1;

                SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
                Editor er=sp.edit();
                er.putInt("SQL_COUNTER", cntr);
                er.commit();

                OutputStreamWriter ot=new OutputStreamWriter(openFileOutput(SetPreferencesActivity.STORE_PREFERENCES,MODE_APPEND));
                ot.write("\nSQLite "+cntr+", "+s.format(new Date()));
                ot.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        Intent it=new Intent(this,MainActivity.class);

        startActivity(it);

    }
//onclick of cancel close sql activity dialog
    public void cancelMessage(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
