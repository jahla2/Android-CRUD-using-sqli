package com.example.ocso_activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    //name of Database
    private static final String DB_NAME = "coursedb";
    //database version
    private static final  int DB_VERSION = 1;
    //table name
    private static final String TABLE_NAME = "mycourses";
    //id column
    private static final String ID_COL = "id";
    //Course name column
    private static final String NAME_COL = "name";
    //course duration
    private static final String DURATION_COL = "duration";
    //course discription colum
    private static final String DESCRIPTION_COL = "description";
    //Tracks column
    private static final  String TRACKS_COL = "tracks";

    //Constructor for Databse handler
    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //method is for creating a database by running a sqlite query
    @Override
    public  void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT) ";

        db.execSQL(query);
    }
    //Method for CREATE
    public void addNewCourse(String courseName, String courseDuration,String courseDescription, String courseTracks) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        //insert to database
        db.insert(TABLE_NAME, null, values);

        //close databse after execution
        db.close();
    }

    //method for checking if table exist already
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
