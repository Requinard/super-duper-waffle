package nl.fontys.sm.superduperwaffle.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by David on 5/26/2016.
 */
public class DatabaseSingleton {
    private static DatabaseInstance dbInstance;

    public static DatabaseInstance getDbInstance() {
        if (dbInstance == null)
            dbInstance = new DatabaseInstance();

        return dbInstance;
    }
}
