package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.os.Bundle;
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
public class ShowDeadlinesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdeadlines);

        Bundle extras = getIntent().getExtras();
        ArrayList<CalendarItem> calendarItems = (ArrayList<CalendarItem>)extras.getSerializable("deadlineList");
        if (extras != null) {
            Log.d("DeadlinesList", "extras != null");
            Log.d("DeadlinesList", "calendarItems.size() == " + calendarItems.size());

            //LinearLayout linearLayout = (LinearLayout)findViewById(R.id.deadlineListLayout);

            ListView listView = (ListView)findViewById(R.id.deadlineListView);
            CalendarItemAdapter calendarItemAdapter = new CalendarItemAdapter(
                    getApplicationContext(),
                    calendarItems
            );

            listView.setAdapter(calendarItemAdapter);

            /*listView.setOnClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                }
            });*/

            /*for(CalendarItem calendarItem : calendarItems) {



                TextView itemString = new TextView(this);

                long val = calendarItem.stopTime;
                Date date=new Date(val);
                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                String dateText = df2.format(date);

                itemString.setText(
                                    "Subject: " + calendarItem.subjectName + "\n" +
                                    "Description: " + calendarItem.description + "\n" +
                                    "Deadline: " + dateText
                );
                itemString.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                linearLayout.addView(itemString);
            }*/
        }
    }
}
