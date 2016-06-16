package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.calendar.CalendarItem;
import nl.fontys.sm.superduperwaffle.calendar.CalendarReader;

/**
 * Created by David on 5/20/2016.
 */
public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        findViewById(R.id.testButton).setOnClickListener(handleClick);
    }

    // Example of how the calendar events for the next months can be retrieved, containing the
    // string "canvas" as calendar ID.
    private View.OnClickListener handleClick = new View.OnClickListener() {
        public void onClick(View arg0) {
            Intent intent = new Intent(LaunchActivity.this, ImportCalendarActivity.class);
            startActivity(intent);
        }
    };
}
