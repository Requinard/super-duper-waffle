package nl.fontys.sm.superduperwaffle.db.models;

import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
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

    @Key
    @Save
    private String name;

    public Course() {

    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getClassesById() {
        return classesById;
    }

    public void setClassesById(List<String> classesById) {
        this.classesById = classesById;
    }

    public List<String> getAssignmentsById() {
        return assignmentsById;
    }

    public void setAssignmentsById(List<String> assignmentsById) {
        this.assignmentsById = assignmentsById;
    }

}
