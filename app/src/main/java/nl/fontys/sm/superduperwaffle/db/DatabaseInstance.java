package nl.fontys.sm.superduperwaffle.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by David on 5/26/2016.
 */
public class DatabaseInstance {
    private FirebaseDatabase database;
    private DatabaseReference referenceUser;
    private DatabaseReference referenceAssignment;

    public DatabaseReference getReferenceAssignment() {
        return referenceAssignment;
    }

    public DatabaseInstance() {

        database = FirebaseDatabase.getInstance();

        referenceUser = database.getReference("users");
        referenceAssignment = database.getReference("assignments");
    }

    public DatabaseReference getReferenceUser() {
        return referenceUser;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }
}