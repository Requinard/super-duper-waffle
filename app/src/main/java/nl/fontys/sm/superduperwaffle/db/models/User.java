package nl.fontys.sm.superduperwaffle.db.models;

import android.provider.ContactsContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Handles users
 */
@IgnoreExtraProperties
public class User implements IModel {
    public String key;

    public User() {
    }

    public User(String username, String email) {

        this.username = username;
        this.email = email;
    }


    public String getKey() {
        return key;
    }

    /**
     * {@inheritDoc}
     *
     * @param key Key that needs to be set on the object
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * {@inheritDoc}
     *
     * @param key Key that the model is stored under
     */
    @Override
    public void read(String key) {
        DatabaseInstance dbInstance = DatabaseSingleton.getDbInstance();

        dbInstance.getReferenceUser().child(key).getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                setUsername(user.username);
                setKey(user.key);
                setEmail(user.email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String username;
    public String email;


    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() {
        DatabaseInstance dbInstance = DatabaseSingleton.getDbInstance();

        if (dbInstance.getReferenceUser().child("username").getRef() == null)
            return;

        setKey(dbInstance.getReferenceUser().push().getKey());

        final Map<String, Object> postValues = toMap();
        final Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put(username, postValues);

        dbInstance.getReferenceUser().updateChildren(childUpdates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("key", key);
        result.put("email", email);

        return result;
    }
}
