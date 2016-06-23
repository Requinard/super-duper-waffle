package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.calendar.CalendarItem;
import nl.fontys.sm.superduperwaffle.calendar.CalendarReader;

/**
 * Created by MT on 10-Jun-16.
 */
public class ImportCalendarActivity extends AppCompatActivity {
    RadioButton currentRb;
    //String rangeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        CalendarReader calendarReader = new CalendarReader();
        List<String> calendarNames = calendarReader.GetCalendars(ImportCalendarActivity.this);

        addRadioButtons(calendarNames);

        Button importButton = (Button) findViewById(R.id.btnImportCalendar);


        importButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // shit dit is lelijk
                EditText etRange = (EditText) findViewById(R.id.etImportRange);
                String rangeTxt = etRange.getText().toString();

                if (currentRb == null) {
                    Toast.makeText(ImportCalendarActivity.this, "Niks geselecteerd",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rangeTxt.isEmpty()) {
                    Toast.makeText(ImportCalendarActivity.this, "Geen range",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(rangeTxt) < 0) {
                    Toast.makeText(ImportCalendarActivity.this, "Ongeldige range",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                printCalendars(
                        currentRb.getText().toString(),
                        Integer.parseInt(rangeTxt)
                );
            }
        });

    }

    private void addRadioButtons(List<String> names) {
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.VERTICAL);

        for (String name : names) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(name);
            //radioButton.setTextColor(Color.WHITE);
            radioGroup.addView(radioButton);
        }
        ((ViewGroup) findViewById(R.id.radiogroup)).addView(radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb = (RadioButton) findViewById(checkedId);
                    currentRb = rb; // hopelijk fuckt dit niet up
                    //printCalendars(rb.getText().toString());
                }
            }
        );

    }


    private void printCalendars(String name, int range) {
        Log.d("Range", "" + range);
        CalendarReader calendarReader = new CalendarReader();
        Calendar calendar = Calendar.getInstance();
        Calendar next = Calendar.getInstance();
        next.add(Calendar.DATE, range);

        ArrayList<CalendarItem> calendarItems;// = new ArrayList<CalendarItem>();
        calendarItems = (ArrayList<CalendarItem>)calendarReader.GetEvents(
                ImportCalendarActivity.this,
                calendar,
                next,
                name);

        Intent intent = new Intent(getBaseContext(), ShowDeadlinesActivity.class);
        intent.putExtra("deadlineList", calendarItems);
        startActivity(intent);
    }

}
