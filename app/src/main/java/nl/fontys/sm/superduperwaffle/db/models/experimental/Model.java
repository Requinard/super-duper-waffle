package nl.fontys.sm.superduperwaffle.db.models.experimental;

import android.graphics.PorterDuff;
import com.google.common.base.Preconditions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;
import nl.fontys.sm.superduperwaffle.db.models.IModel;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by David on 6/2/2016.
 */
abstract public class Model implements IModel {

    @Save
    @Key
    private String key;

    /**
     * Pushes the object to the database
     */
    @Override
    public void save() {
        // Get the database
        DatabaseInstance dbInstance = DatabaseSingleton.getDbInstance();

        // Get our model
        DatabaseReference reference = dbInstance.getDatabase().getReference(this.getClass().getSimpleName());

        if (key == null)
            key = reference.push().getKey();

        //todo: add logic to find pre-existing entries

        // Get the value to put

        // Save it under unique keys
        Map<String, Object> keyToModel = new HashMap<>();

        keyToModel.put(key, toMap());

        reference.child("key").updateChildren(keyToModel);
        // Save all under keys
        /**
         * What it looks like
         *
         * @Key
         * - @Key.value
         * - - unique key to find object with
         * repeat
         */
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Key.class)) {
                try {
                    field.setAccessible(true);

                    // Get the string held inside the field
                    String node = field.get(this).toString();

                    // Create a new sub object with the field as key
                    Map<String, Object> fieldToKey = new HashMap<>();

                    fieldToKey.put(node, key);

                    // Get a reference with our field and then push the new child
                    reference.child(field.getName()).updateChildren(fieldToKey);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Transform the object to a map so we can push it into the database
     *
     * @return Key-value map that can be written as a child node
     */
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        // Get fields in subclass
        Field[] fields = this.getClass().getDeclaredFields();

        // iterate through fields
        for (Field field : fields) {
            // If field is not transient
            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);

                try {
                    map.put(field.getName(), field.get(this));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
