package nl.fontys.sm.superduperwaffle.db.models;

import java.util.List;
import java.util.Map;

/**
 * Holds the information for a specific course
 */
public class Course implements IModel {
    /**
     * Holds the Keys of classes
     */
    private List<String> classesById;

    /**
     * Holds the keys of assignments
     */
    private List<String> assignmentsById;

    @Override
    public void save() {

    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }

    @Override
    public void setKey(String key) {

    }

    @Override
    public void read(String key) {

    }
}
