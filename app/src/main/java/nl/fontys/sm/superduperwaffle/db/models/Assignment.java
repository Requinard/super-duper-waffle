package nl.fontys.sm.superduperwaffle.db.models;

import java.util.List;
import java.util.Map;

/**
 * Holds the data for a specific assignment
 */
public class Assignment implements IModel {
    /**
     * Key to school class vlaue
     */
    private String courseById;

    /**
     * Key to measurements relating to this assignment
     */
    private List<String> measurementsById;

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
