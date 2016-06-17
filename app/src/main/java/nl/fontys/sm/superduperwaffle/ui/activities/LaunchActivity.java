package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.db.models.User;
import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.util.ThreadService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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

        ThreadService.queue(new Runnable() {
            @Override
            public void run() {
                FutureTask task = Model.find("requinard", User.class, "username");

                try {
                    User user = (User) task.get(10L, TimeUnit.SECONDS);

                    Log.d("WHAT", user.getEmail());
                } catch (InterruptedException | TimeoutException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.testButton).setOnClickListener(handleClick);
    }

    private View.OnClickListener handleClick = new View.OnClickListener() {
        public void onClick(View arg0) {
            Intent intent = new Intent(LaunchActivity.this, ImportCalendarActivity.class);
            startActivity(intent);
        }
    };
}
