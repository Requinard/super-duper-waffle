package nl.fontys.sm.superduperwaffle.db.models;

import android.graphics.PorterDuff;
import android.provider.ContactsContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import nl.fontys.sm.superduperwaffle.db.DatabaseInstance;
import nl.fontys.sm.superduperwaffle.db.DatabaseSingleton;
import nl.fontys.sm.superduperwaffle.db.models.experimental.Model;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Key;
import nl.fontys.sm.superduperwaffle.db.models.experimental.annotations.Save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Handles users
 */
@IgnoreExtraProperties
public class User extends Model {
    @Save
    @Key
    private String username;

    @Key
    @Save
    private String email;

    @Save
    List<Course> courses = new ArrayList<Course>();

    public User() {
    }

    public User(String username, String email) {

        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Get all the courses from the user
    public List<Course> getCourses() {
        return courses;
    }

    //Add course to the list of courses of the user (only if the course isn't already added)
    public void addCourse(Course course) {
        if(courses.size() != 0) {
            for (Course c : courses) {
                if (!c.getName().equals(course.getName())) {
                    courses.add(course);
                    course.save();
                }
            }
        } else {
            courses.add(course);
            course.save();
        }
    }
}
