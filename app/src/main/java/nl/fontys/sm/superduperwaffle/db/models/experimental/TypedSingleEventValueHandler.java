package nl.fontys.sm.superduperwaffle.db.models.experimental;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 6/10/2016.
 */
public class TypedSingleEventValueHandler implements ValueEventListener {
    private Map<String, Object> val = new HashMap<>();

    public Map<String, Object> getVal() {
        return val;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<Map<String, Object>> typeIndicator = new GenericTypeIndicator<Map<String, Object>>() {
        };

        String t = dataSnapshot.getValue(String.class);

        Log.d("DB", t);

        synchronized (this) {
            notifyAll();
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
