package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
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
            Log.i("Debug: ", "Click");

            CalendarReader calendarReader = new CalendarReader();
            Calendar calendar = Calendar.getInstance();
            Calendar next = Calendar.getInstance();
            next.add(Calendar.DATE, 30);
            Log.i("Debug: ", "Got Cal+30");

            List<CalendarItem> calendarItems = calendarReader.getEvents(
                    LaunchActivity.this,
                    calendar,
                    next,
                    "canvas");
            Log.i("Debug: ", "Got Calendar");

            for(CalendarItem calendarItem : calendarItems) {
                /*if (calendarItem.calendarName.toLowerCase().contains("canvas")) {
                    Log.i("Calendar: ",
                            "Subject: " + calendarItem.subjectName + "\n" +
                                    "Description: " + calendarItem.description + "\n"
                    );
                }*/
                Log.i("Calendar: ",
                        "Subject: " + calendarItem.subjectName + "\n" +
                                "Description: " + calendarItem.description + "\n"
                );
            }
        }
    };
}
