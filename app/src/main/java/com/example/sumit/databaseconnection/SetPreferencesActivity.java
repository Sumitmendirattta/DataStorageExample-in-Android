package com.example.sumit.databaseconnection;

/**
 * Created by Sumit on 3/2/2016.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.view.View;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.widget.EditText;
import android.content.Intent;
import android.preference.PreferenceManager;

public class SetPreferencesActivity extends Activity {

    public final static String STORE_PREFERENCES="storePrefFinal.txt";
   // to count activities
    public int cntr=0;
    private SimpleDateFormat s=new SimpleDateFormat("MM/dd/yyyy-hh:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load set preference layout
        setContentView(R.layout.a_preferences);
        //call shared pref activity
        SharedPreferences sps = PreferenceManager.getDefaultSharedPreferences(this);
        cntr=sps.getInt("COUNTER", 0);

    }

    //onresume call saved preference
    @Override
    public void onResume()
    {
        super.onResume();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        cntr=sp.getInt("COUNTER", 0);
    }

    // when clicked on onsave save
    public void onSave(View view)
    {
        //save  preferences
        EditText n_t=(EditText)findViewById(R.id.bookname_value);
        String name_t=n_t.getText().toString();
        EditText at=(EditText)findViewById(R.id.bookauthor_value);
        String author=at.getText().toString();
        EditText dt=(EditText)findViewById(R.id.description_value);
        String desc=dt.getText().toString();

        if(name_t!=null && author!=null && desc !=null)
        {
            try
            {
                //increment counter
                cntr+=1;

                SharedPreferences sps=PreferenceManager.getDefaultSharedPreferences(this);
                //create object of editor class
                Editor e=sps.edit();
                e.putInt("COUNTER", cntr);
                e.commit();
                //write on output stream
                OutputStreamWriter o=new OutputStreamWriter(openFileOutput(STORE_PREFERENCES,MODE_APPEND));
                //append messgae with data and time
                String msg="\nSaved Preference "+cntr+", "+s.format(new Date());
                o.write(msg);
                o.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


        }
        //get contents on resume
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
    //on click of on cancel close current preference activity
    public void onCancel(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
