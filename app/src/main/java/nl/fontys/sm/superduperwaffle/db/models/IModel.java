package nl.fontys.sm.superduperwaffle.db.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by David on 5/26/2016.
 */
public interface IModel {
    /**
     * Pushes the object to the database
     */
    void save();

    /**
     * Transform the object to a map so we can push it into the database
     *
     * @return Key-value map that can be written as a child node
     */
    Map<String, Object> toMap();
}
