package nl.fontys.sm.superduperwaffle.db.models.experimental;

import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;
import nl.fontys.sm.superduperwaffle.db.models.IModel;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;
import nl.fontys.sm.superduperwaffle.util.ThreadService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by David on 6/2/2016.
 */
abstract public class Model implements IModel {
    private static final String PREFIX = "DB";

    @Save
    @Key
    /**
     * Unique key to distinguis between objects
     */
    private String key;

    /**
     * Gets a model from the db based on a specific key value lookup
     *
     * @param key     Key to perform a lookup on
     * @param current Class that needs to be returned
     * @param field   Field that we're performing a lookup on
     * @param <T>     Type of class to be returned
     * @return FutureTask that is in queue for execution. No need to run it yourself
     */
    public static <T> FutureTask<T> find(final String key, final Class<T> current, String field) {
        Log.d(PREFIX, String.format("Retrieving data from database. Class %s Field %s key %s", current.getSimpleName(), field, key));
        TypedSingleValueEventListener<String> handler = new TypedSingleValueEventListener<>(String.class);

        FirebaseDatabase.getInstance()
                .getReference(current.getSimpleName())
                .child(field)
                .child(key)
                .addListenerForSingleValueEvent(handler);

        synchronized (handler) {
            try {
                Log.d(PREFIX, "Waiting on Key lookup to complete");
                handler.wait();
                Log.d(PREFIX, "Key lookup completed");
            } catch (InterruptedException e) {
                Log.d(PREFIX, e.toString());
            }
        }

        Log.d(PREFIX, "Now going to retrieve object trhough key");
        return find(handler.getVal(), current);
    }

    /**
     * Gets a model from the db based on the key
     *
     * @param key     key to find it (unique)
     * @param current class to instantiate
     * @param <T>     Type of class to instantiate
     * @return FutureTask that is in queue for execution. No need to run it yourself
     */
    public static <T> FutureTask<T> find(final String key, final Class<T> current) {
        Log.d(PREFIX, String.format("Performing key lookup on Class %s. Key is %s", current.getSimpleName(), key));

        Log.d(PREFIX, String.format("Creating FutureTask"));

        final FutureTask<T> futureTask = new FutureTask<T>(new Callable<T>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public T call() throws Exception {
                Log.d(PREFIX, String.format("FutureTask is being executed at %s", System.currentTimeMillis()));

                TypedSingleValueEventListener<T> handler = new TypedSingleValueEventListener<>(current);

                FirebaseDatabase firebaseDatabase = DatabaseSingleton.getDbInstance().getDatabase();
                firebaseDatabase.getReference(current.getSimpleName())
                        .child("key")
                        .child(key)
                        .addListenerForSingleValueEvent(handler);


                synchronized (handler) {
                    Log.d(PREFIX, "Waiting on database callback to complete");
                    handler.wait();
                    Log.d(PREFIX, "Database callback completed");
                }
                return handler.getVal();
            }
        });

        Log.d(PREFIX, String.format("Submitting task at %s", System.currentTimeMillis()));
        ThreadService.submit(futureTask);

        return futureTask;
    }

    /**
     * Pushes the object to the database
     */
    @Override
    public void save() {
        Log.d(PREFIX, String.format("Saving instance of %s at %s", this.getClass().getName(), System.currentTimeMillis()));

        // Get the database
        DatabaseInstance dbInstance = DatabaseSingleton.getDbInstance();

        // Get our model
        DatabaseReference reference = dbInstance.getDatabase().getReference(this.getClass().getSimpleName());

        //todo: add logic to find pre-existing entries

        // Get the value to put

        // Save it under unique keys
        Map<String, Object> keyToModel = new HashMap<>();

        keyToModel.put(key, toMap());

        Log.d(PREFIX, "Updating key child for this class");
        reference.child("key").updateChildren(keyToModel);


        /**
         * What it looks like
         *
         * @Key
         * - @Key.value
         * - - unique key to find object with
         * repeat
         */

        Log.d(PREFIX, "Now saving extra key fields");

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Key.class)) {
                Log.d(PREFIX, String.format("Field %s is classified as a key", field.getName()));
                try {
                    field.setAccessible(true);

                    // Get the string held inside the field
                    String node = field.get(this).toString();

                    // Create a new sub object with the field as key
                    Map<String, Object> fieldToKey = new HashMap<>();

                    fieldToKey.put(node, key);
                    Log.d(PREFIX, "Pushing subkey to database");
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
        Log.d(PREFIX, String.format("Marshalling class %s", this.getClass().getName()));

        Map<String, Object> map = new HashMap<>();

        // Get fields in subclass
        Field[] fields = this.getClass().getDeclaredFields();

        // iterate through fields
        for (Field field : fields) {
            // If field is not transient
            if (field.isAnnotationPresent(Save.class)) {
                Log.d(PREFIX, String.format("Saving field %s", field.getName()));

                field.setAccessible(true);

                try {
                    map.put(field.getName(), field.get(this));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(PREFIX, "Marshalling complete");
        return map;
    }
}
