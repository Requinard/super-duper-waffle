package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.calendar.CalendarItem;
import nl.fontys.sm.superduperwaffle.ui.adapters.CalendarItemAdapter;

/**
 * Created by MT on 22-Jun-16.
 */
public class ShowDeadlinesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdeadlines);

        Bundle extras = getIntent().getExtras();
        // why final ;_;
        final ArrayList<CalendarItem> calendarItems =
                (ArrayList<CalendarItem>)extras.getSerializable("deadlineList");

        if (extras != null) {
            Log.d("DeadlinesList", "extras != null");
            Log.d("DeadlinesList", "calendarItems.size() == " + calendarItems.size());

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.deadlineListLayout);

            ListView listView = (ListView)findViewById(R.id.deadlineListView);

            // shit
            if (calendarItems.size() == 0) {
                TextView tv = new TextView(getBaseContext());
                tv.setText("Geen deadlines gevonden");
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(20); // blaaaah
                tv.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                linearLayout.addView(tv, 0);
                Log.d("CalendarItems", "None");
                return;
            }

            CalendarItemAdapter calendarItemAdapter = new CalendarItemAdapter(
                    getApplicationContext(),
                    calendarItems
            );

            listView.setAdapter(calendarItemAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ShowDeadlineDetailsActivity.class);
                    CalendarItem calendarItem = calendarItems.get(position);
                    intent.putExtra("coursename", calendarItem.subjectName);    // string
                    intent.putExtra("deadline", calendarItem.stopTime);         // long date
                    intent.putExtra("description", calendarItem.description);   // string (big)
                    startActivity(intent);
                }
            });
        }
    }
}
