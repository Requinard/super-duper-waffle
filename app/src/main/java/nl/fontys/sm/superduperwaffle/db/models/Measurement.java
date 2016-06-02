package nl.fontys.sm.superduperwaffle.db.models;

import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

import java.util.Map;

/**
 * Holds the data for a measurement
 */
public class Measurement extends Model {
    /**
     * Type of measurement
     */
    @Save
    private MeasurementType type;

    /**
     * Key to an Assignment value
     */
    @Save
    @Key
    private String assignmentKey;

    /**
     * Key to a user value
     */
    @Save
    @Key
    private String userName;

    @Override
    public void save() {

    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }
}
