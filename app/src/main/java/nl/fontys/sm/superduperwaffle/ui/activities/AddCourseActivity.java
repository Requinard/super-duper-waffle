package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.db.models.Course;
import nl.fontys.sm.superduperwaffle.db.models.User;
import nl.fontys.sm.superduperwaffle.db.models.experimental.userLogged;

/**
 * Created by Antonio M on 16-6-2016.
 */
public class AddCourseActivity extends Activity {

    String[] testCourses = { "GSO3","JSF31","JSF32","JCC","SAM3","Proftaak", "VSA", "PPO", "KPO", };
    User testUser = new User("Frank", "a@hotmail.com");
    Course course;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, testCourses);
        //Find TextView control
        final AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.languages);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);

        final TextView tvCourses = (TextView) findViewById(R.id.tvCourses);
        Button btnAddCourse = (Button) findViewById(R.id.btnAddCourse);

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course = new Course(acTextView.getText().toString());
                try {
                    testUser.addCourse(course);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

                String courses = "";
                for (Course c : testUser.getCourses()) {
                    if (courses.equals(""))
                        courses = c.getName();
                    else
                        courses += ", " + c.getName();
                }

                final AlertDialog.Builder adAddCourse = new AlertDialog.Builder(AddCourseActivity.this);
                adAddCourse.setTitle("Succes");
                adAddCourse.setMessage("The course is added to your courses correctly!");
                adAddCourse.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                adAddCourse.show();

                tvCourses.setText(courses);
            }
        });
    }

}
