package nl.fontys.sm.superduperwaffle.db.models.experimental;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;
import nl.fontys.sm.superduperwaffle.db.models.IModel;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by David on 6/2/2016.
 */
abstract public class Model implements IModel {

    @Save
    @Key
    /**
     * Unique key to distinguis between objects
     */
    private String key;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    /**
     * Indicates whether the model is readable or if it's still pending initialization
     */
    @Deprecated
    private boolean isReady = false;

    public static <T> FutureTask<T> find(final String key, final Class<T> current) {
        final FutureTask<T> futureTask = new FutureTask<T>(new Callable<T>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public T call() throws Exception {
                SingleValueEventListener<T> handler = new SingleValueEventListener<>(current);

                FirebaseDatabase firebaseDatabase = DatabaseSingleton.getDbInstance().getDatabase();
                firebaseDatabase.getReference(current.getSimpleName())
                        .child("key")
                        .child(key)
                        .addListenerForSingleValueEvent(handler);

                synchronized (handler) {
                    handler.wait();
                }
                return handler.getVal();
            }
        });

        threadPool.submit(futureTask);

        return futureTask;
    }

    private <T> void updateFields(DataSnapshot snapshot, Class<T> type) {
        try {
            BeanUtils.copyProperties((T) this, snapshot.getValue(type));
            isReady = true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

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
