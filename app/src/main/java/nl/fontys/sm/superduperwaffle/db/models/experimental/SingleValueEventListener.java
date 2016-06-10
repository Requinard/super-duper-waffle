package nl.fontys.sm.superduperwaffle.db.models.experimental;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by David on 6/2/2016.
 */
public class SingleValueEventListener<T> implements ValueEventListener {
    private T val;
    private Class<T> type;

    public boolean isDone() {
        return val != null;
    }

    public SingleValueEventListener(Class<T> type) {
        this.type = type;
    }

    public T getVal() {
        return val;
    }

    @Override

    public void onDataChange(DataSnapshot dataSnapshot) {
        val = dataSnapshot.getValue(type);

        synchronized (this) {
            this.notifyAll();
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
