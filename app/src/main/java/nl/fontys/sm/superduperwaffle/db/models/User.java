package nl.fontys.sm.superduperwaffle.db.models;

import android.graphics.PorterDuff;
import android.provider.ContactsContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;
import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Handles users
 */
@IgnoreExtraProperties
public class User extends Model {
    @Save
    @Key
    private String username;

    @Key
    @Save
    private String email;

    public User() {
    }

    public User(String username, String email) {

        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
