package nl.fontys.sm.superduperwaffle.db.models;

import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

import java.util.List;
import java.util.Map;

/**
 * Holds the users in a class
 */
public class SchoolClass extends Model {
    /**
     * List of students related to this class by keys
     */
    @Save
    private List<String> studentsByKeys;

    /**
     * List of courses related to this class by keys
     */
    @Save
    private List<String> coursesByKeys;

    @Save @Key
    private String name;
}
