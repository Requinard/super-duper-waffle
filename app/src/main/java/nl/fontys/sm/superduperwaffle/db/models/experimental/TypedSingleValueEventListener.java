package nl.fontys.sm.superduperwaffle.db.models.experimental;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by David on 6/2/2016.
 */
public class TypedSingleValueEventListener<T> implements ValueEventListener {
    private static final String PREFIX = "DBLIST";

    private T val;
    private Class<T> type;

    public boolean isDone() {
        return val != null;
    }

    public TypedSingleValueEventListener(Class<T> type) {
        this.type = type;
    }

    public T getVal() {
        return val;
    }

    @Override

    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d(PREFIX, "Callback being executed");

        val = dataSnapshot.getValue(type);

        if (val == null) {
            Log.w(PREFIX, "No value was found!");
        } else {
            Log.d(PREFIX, "Succesfully retrieved and cast class");
        }


        synchronized (this) {
            Log.d(PREFIX, String.format("Notifiying listeners at %s", System.currentTimeMillis()));
            this.notifyAll();
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
