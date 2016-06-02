package nl.fontys.sm.superduperwaffle.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import nl.fontys.sm.superduperwaffle.R;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;
import nl.fontys.sm.superduperwaffle.db.models.Assignment;
import nl.fontys.sm.superduperwaffle.db.models.User;
import nl.fontys.sm.superduperwaffle.db.models.experimental.TestModel;

import java.util.LinkedList;

/**
 * Created by David on 5/20/2016.
 */
public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        new User().find("-KJFjKo7PxilTUiLWaLi", User.class);
    }
}
