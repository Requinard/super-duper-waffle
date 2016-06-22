package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.calendar.CalendarItem;
import nl.fontys.sm.superduperwaffle.calendar.CalendarReader;

/**
 * Created by MT on 10-Jun-16.
 */
public class ImportCalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        CalendarReader calendarReader = new CalendarReader();
        List<String> calendarNames = calendarReader.GetCalendars(ImportCalendarActivity.this);

        addRadioButtons(calendarNames);

    }

    private void addRadioButtons(List<String> names) {
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.VERTICAL);

        for (String name : names) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(name);
            radioGroup.addView(radioButton);
        }
        ((ViewGroup) findViewById(R.id.radiogroup)).addView(radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb = (RadioButton) findViewById(checkedId);
                    printCalendars(rb.getText().toString());
                }
            }
        );
    }


    private void printCalendars(String name) {
        CalendarReader calendarReader = new CalendarReader();
        Calendar calendar = Calendar.getInstance();
        Calendar next = Calendar.getInstance();
        next.add(Calendar.DATE, 30);

        /*List<CalendarItem> calendarItems = calendarReader.GetEvents(
                ImportCalendarActivity.this,
                calendar,
                next,
                name);*/
        ArrayList<CalendarItem> calendarItems = new ArrayList<CalendarItem>();
        calendarItems = (ArrayList<CalendarItem>)calendarReader.GetEvents(
                ImportCalendarActivity.this,
                calendar,
                next,
                name);

        Intent intent = new Intent(getBaseContext(), ShowDeadlinesActivity.class);
        intent.putExtra("deadlineList", calendarItems);
        startActivity(intent);

        //for(CalendarItem calendarItem : calendarItems) {
            /*Log.d("Calendar: ",
                    "Subject: " + calendarItem.subjectName + "\n" +
                            "Description: " + calendarItem.description + "\n"
            );*/
        //}
    }

}
