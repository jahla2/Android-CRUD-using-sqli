package com.example.ocso_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCourseActivity extends AppCompatActivity {

    //variable
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button updateCourseBtn, deleteCourseBtn;
    private DBHandler dbHandler;
    String courseName, courseDesc, courseDuration, courseTracks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        //assigning all variables
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);

        //including db handler
        dbHandler = new DBHandler(UpdateCourseActivity.this);

        //sasalo ng data na pinasa galing kay Adapter class
        courseName = getIntent().getStringExtra("name");
        courseDesc = getIntent().getStringExtra("description");
        courseDuration = getIntent().getStringExtra("duration");
        courseTracks = getIntent().getStringExtra("tracks");

        //setting data from edit text on update activity
        courseNameEdt.setText(courseName);
        courseDescriptionEdt.setText(courseDesc);
        courseTracksEdt.setText(courseTracks);
        courseDurationEdt.setText(courseDuration);

        //Onclick Listener for update button
        updateCourseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //calling method for update course
                //passing all our edit text values
                dbHandler.updateCourse(courseName,courseNameEdt.getText().toString(),
                        courseDescriptionEdt.getText().toString(),
                        courseTracksEdt.getText().toString(),
                        courseDescriptionEdt.getText().toString());

                //Display toast Message that course has been updated
                Toast.makeText(UpdateCourseActivity.this, "Couse Updated..", Toast.LENGTH_SHORT).show();

                //Returning to main activity View
                Intent i = new Intent(UpdateCourseActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        //


    }
}