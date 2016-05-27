package nl.fontys.sm.superduperwaffle.db.models;

import java.util.List;
import java.util.Map;

/**
 * Holds the users in a class
 */
public class SchoolClass implements IModel {
    /**
     * List of students related to this class by keys
     */
    private List<String> studentsByKeys;

    /**
     * List of courses related to this class by keys
     */
    private List<String> coursesByKeys;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() {

    }

    /**
     * {@inheritDoc}
     *
     * @return Key-value map that can be written as a child node
     */
    @Override
    public Map<String, Object> toMap() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param key Key that needs to be set on the object
     */
    @Override
    public void setKey(String key) {

    }

    /**
     * {@inheritDoc}
     *
     * @param key Key that the model is stored under
     */
    @Override
    public void read(String key) {

    }
}
