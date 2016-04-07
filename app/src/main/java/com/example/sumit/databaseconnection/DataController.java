package com.example.sumit.databaseconnection;

/**
 * Created by Sumit on 3/2/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataController
{

    //define variables
    public static final String MSG="Message";
    public static final int DB_V=4;
    public static final String TBL_CREATE="create table M_Table (Message text not null);";
    public static final String DB_NAME="db2.db";
    public static final String TBL_N="M_Table";


    //define variables
    DataBaseHelper dbHelper;
    Context context;
    SQLiteDatabase db;



    // define function retrieve variables
    public Cursor retrieve()
    {
        return db.query(TBL_N, new String[]{MSG}, null, null, null, null, null);
    }


    //dta controller
    public DataController(Context context)
    {
        this.context=context;
        dbHelper=new DataBaseHelper(context);
    }

    //open data controller
    public DataController open()
    {
        db=dbHelper.getWritableDatabase();
        return this;
    }

    //close activity
    public void close()
    {
        dbHelper.close();
    }

    // database helper
    private static class DataBaseHelper extends SQLiteOpenHelper
    {
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS M_Table");
            onCreate(db);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            try
            {
                db.execSQL(TBL_CREATE);
            }
            catch(SQLiteException e)
            {
                e.printStackTrace();
            }
        }

        public DataBaseHelper(Context context)
        {
            super(context, DB_NAME, null, DB_V);
        }



    }

    //function to insert data
    public long insert(String message)
    {
        ContentValues content=new ContentValues();
        content.put(MSG, message);
        return db.insertOrThrow(TBL_N, null, content);
    }




}