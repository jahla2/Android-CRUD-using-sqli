package com.example.ocso_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variable for editext, button and dbhandler
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdit;
    private Button addCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning all variables Declared above
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdit = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);


        //variable for passing data from DBHandler
        dbHandler = new DBHandler(MainActivity.this);

        //Onclick Listiner for Add course button
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Get data from all input fields
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdit.getText().toString();

                //Validation if fields are empty or not
                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter all the data..",Toast.LENGTH_SHORT).show();
                    return;
                }

                //calling a method to add and data pass all values to sqlite
                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);

                //After adding data we display a Toast message.
                Toast.makeText(MainActivity.this, "Course has been added.",Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdit.setText("");

            }

        });
    }
}