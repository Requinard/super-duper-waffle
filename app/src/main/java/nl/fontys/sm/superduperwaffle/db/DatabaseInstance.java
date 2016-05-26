package nl.fontys.sm.superduperwaffle.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by David on 5/26/2016.
 */
public class DatabaseInstance {
    private FirebaseDatabase database;
    private DatabaseReference referenceUser;

    public DatabaseInstance() {

        database = FirebaseDatabase.getInstance();

        referenceUser = database.getReference("users");
    }

    public DatabaseReference getReferenceUser() {
        return referenceUser;
    }
}