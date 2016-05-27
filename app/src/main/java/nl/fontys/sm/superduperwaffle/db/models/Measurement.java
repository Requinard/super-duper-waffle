package nl.fontys.sm.superduperwaffle.db.models;

import java.util.Map;

/**
 * Holds the data for a measurement
 */
public class Measurement implements IModel {
    /**
     * Type of measurement
     */
    private MeasurementType type;

    /**
     * Key to an Assignment value
     */
    private String assignmentKey;

    /**
     * Key to a user value
     */
    private String userKey;

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
