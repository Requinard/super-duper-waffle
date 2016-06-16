package nl.fontys.sm.superduperwaffle.db.models;

import com.google.common.base.Preconditions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;
import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Holds the data for a specific assignment
 */
public class Assignment extends Model {
    @Save
    @Key
    private String assignmentName;

    /**
     * Key to school class vlaue
     */
    @Save
    @Key
    private String courseById;

    /**
     * Key to measurements relating to this assignment
     */
    @Save
    private List<String> measurementsById = new LinkedList<>();

}
