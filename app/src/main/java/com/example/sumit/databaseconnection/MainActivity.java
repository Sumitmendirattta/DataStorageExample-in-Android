package com.example.sumit.databaseconnection;

/**
 * Created by Sumit on 3/2/2016.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
// onresume ethod to call activity

    @Override
    protected void onResume()
    {
        super.onResume();
        try
        {
            InputStream in=openFileInput(SetPreferencesActivity.STORE_PREFERENCES);
            if(in!=null)
            {
                InputStreamReader tempo=new InputStreamReader(in);
                BufferedReader r=new BufferedReader(tempo);
                String stp;
                StringBuilder buf=new StringBuilder();
                while((stp=r.readLine())!=null)
                {
                    buf.append(stp +"\n");
                }
                in.close();
                TextView sPref=(TextView)findViewById(R.id.saved_data);
                sPref.setText(buf.toString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    //open preference activity java file
    public void openPreference(View view)
    {
        Intent intent=new Intent(this,SetPreferencesActivity.class);
        startActivity(intent);
    }



    // close app function
    //
    public void exitApp(View view)
    {
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        System.exit(0);
    }



    // save the data in database
    public void saveInDatabase(View view)
    {
        Intent intent =new Intent(this,SQLiteActivity.class);
        startActivity(intent);
    }


}
