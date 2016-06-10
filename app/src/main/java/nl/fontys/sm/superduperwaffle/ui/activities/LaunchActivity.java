package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.db.models.User;
import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.util.ThreadService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
                FutureTask task = Model.find("-KJFjKo7PxilTUiLWaLi", User.class);

                try {
                    User user = (User) task.get(10L, TimeUnit.SECONDS);

                    Log.d("WHAT", user.getEmail());
                } catch (InterruptedException | TimeoutException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
