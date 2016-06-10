package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import nl.fontys.sm.superduperwaffle.R;

/**
 * Created by MT on 10-Jun-16.
 */
public class ImportCalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);
    }



    /*
    CalendarReader calendarReader = new CalendarReader();
    Calendar calendar = Calendar.getInstance();
    Calendar next = Calendar.getInstance();
    next.add(Calendar.DATE, 30);

    List<CalendarItem> calendarItems = calendarReader.getEvents(
            LaunchActivity.this,
            calendar,
            next,
            "canvas");

    for(CalendarItem calendarItem : calendarItems) {
        Log.i("Calendar: ",
                "Subject: " + calendarItem.subjectName + "\n" +
                        "Description: " + calendarItem.description + "\n"
        );
    }*/
}
