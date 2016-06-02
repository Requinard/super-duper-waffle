package nl.fontys.sm.superduperwaffle.db.models;

import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

import java.util.List;
import java.util.Map;

/**
 * Holds the information for a specific course
 */
public class Course extends Model {
    /**
     * Holds the Keys of classes
     */
    @Save
    private List<String> classesById;

    /**
     * Holds the keys of assignments
     */
    @Save
    private List<String> assignmentsById;

}
