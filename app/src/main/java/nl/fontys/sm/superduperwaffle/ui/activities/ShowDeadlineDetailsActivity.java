package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import nl.fontys.sm.superduperwaffle.R;

/**
 * Created by MT on 22-Jun-16.
 */
public class ShowDeadlineDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetail);
        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return; // fuck
        }

        TextView courseName = (TextView) findViewById(R.id.tvCourseName);
        TextView deadline = (TextView) findViewById(R.id.tvDeadline);
        TextView description = (TextView) findViewById(R.id.tvDescription);

        courseName.setText(extras.getString("coursename"));
        description.setText(extras.getString("description"));

        Date date=new Date(extras.getLong("deadline"));
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(date);
        deadline.setText("Deadline: " + dateText);

    }

}
