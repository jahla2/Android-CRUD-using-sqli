package com.example.ocso_activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    //course description column
    private static final String DESCRIPTION_COL = "description";
    //Tracks column
    private static final  String TRACKS_COL = "tracks";

    //Constructor for Database handler
    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //method is for creating a database by running a sqlite query when first install
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

    //Method for Adding new Record
    public void addNewCourse(String courseName, String courseDuration,String courseDescription, String courseTracks) {
        //Writing to database
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

    //method for reading all records
    public ArrayList<CourseModal>  readCourses() {
        //Reading to database
        SQLiteDatabase db = this.getReadableDatabase();

        //Creating Cursor Query to read data
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        //Creating new Array List
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        //moving cursor to first position
        if (cursorCourses.moveToFirst()){
            do
            {
                //Adding data from cursor to array list
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
            //moving cursor to next
        }
        //at last closing cursor retruning our array list
        cursorCourses.close();
        return  courseModalArrayList;
    }

    //method for updating Records
    public void updateCourse(String originalCourseName, String courseName, String courseDescription, String courseTracks, String courseDuration)
    {
        //calling method for Update writable database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //passing all values gamit ang key value pair
        values.put(NAME_COL,courseName);
        values.put(DURATION_COL,courseDuration);
        values.put(DESCRIPTION_COL,courseDescription);
        values.put(TRACKS_COL,courseTracks);

        //Calling update method to update database
        //Comparing it with name of courses in original variable
        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    //Method for Deleting Records
    public void deleteCourse(String courseName)
    {
        //declare variable to write database
        SQLiteDatabase db = this.getWritableDatabase();

        //Calling method for delete records comparing it in courseName
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }

    //method for checking if table exist already
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
